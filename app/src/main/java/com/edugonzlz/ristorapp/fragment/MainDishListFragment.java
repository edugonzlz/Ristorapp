package com.edugonzlz.ristorapp.fragment;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewSwitcher;

import com.edugonzlz.ristorapp.R;
import com.edugonzlz.ristorapp.activity.DishActivity;
import com.edugonzlz.ristorapp.adapter.DishListRecyclerViewAdapter;
import com.edugonzlz.ristorapp.model.DishListModel;
import com.edugonzlz.ristorapp.model.DishModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;

/**
 * Created by Edu on 8/12/16.
 */

public class MainDishListFragment extends Fragment implements DishListRecyclerViewAdapter.OnDishClickListener{

    private RecyclerView mList;
    private ViewSwitcher mViewSwitcher;

    private DishModel mDish;
    private LinkedList<DishModel> mMainDishList;

    private static final int LOADING_VIEW_INDEX = 0;
    private static final int DISHLIST_VIEW_INDEX = 1;

    private static final String ARG_TABLE_INDEX = "ARG_TABLE_INDEX";
    private int mTableIndex;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() !=null) {
            mTableIndex = getArguments().getInt(ARG_TABLE_INDEX);
        }
    }

    public static MainDishListFragment newInstance(int tableIndex) {
        Bundle arguments = new Bundle();
        arguments.putInt(ARG_TABLE_INDEX, tableIndex);

        MainDishListFragment mainDishListFragment = new MainDishListFragment();
        mainDishListFragment.setArguments(arguments);

        return mainDishListFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_main_dish_list, container, false);

        mViewSwitcher = (ViewSwitcher) root.findViewById(R.id.view_switcher);
        mViewSwitcher.setInAnimation(getActivity(), android.R.anim.fade_in);
        mViewSwitcher.setOutAnimation(getActivity(), android.R.anim.fade_out);

        mList = (RecyclerView) root.findViewById(R.id.recycler_view);
        mList.setLayoutManager(new LinearLayoutManager(getActivity()));
        //        mList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mList.setItemAnimator(new DefaultItemAnimator());
        mList.setAdapter(new DishListRecyclerViewAdapter(new LinkedList<DishModel>(), this));

        setDishList(mMainDishList);
        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onDishClick(int position, DishModel dish, View view) {

        // Vamos a mostrar la vista de detalle
        Intent intent = new Intent(getActivity(), DishActivity.class);
        intent.putExtra(DishActivity.EXTRA_DISH, dish);
        intent.putExtra(DishActivity.EXTRA_TABLE_INDEX, mTableIndex);

        startActivity(intent,
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                        getActivity(),
                        view,
                        getString(R.string.transition_to_detail) // El nombre dentro de la vista destino
                ).toBundle());
    }

    public void downloadMainDishList() {
        AsyncTask<DishModel, Integer, LinkedList<DishModel>> dishListDownloader = new AsyncTask<DishModel, Integer, LinkedList<DishModel>>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                mViewSwitcher.setDisplayedChild(LOADING_VIEW_INDEX);
            }

            @Override
            protected LinkedList<DishModel> doInBackground(DishModel... dishModels) {
                URL url = null;
                InputStream input = null;

                try {
                    url = new URL(String.format("http://www.mocky.io/v2/584c4e2f1200001921372b11"));
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.connect();
                    int responseLength = con.getContentLength();
                    byte data[] = new byte[1024];
                    long currentBytes = 0;
                    int downloadedBytes;
                    input = con.getInputStream();
                    StringBuilder sb = new StringBuilder();
                    while ((downloadedBytes = input.read(data)) != -1 && !isCancelled()) {
                        sb.append(new String(data, 0, downloadedBytes));

                        publishProgress((int)(currentBytes * 100) / responseLength);
                    }

                    if (isCancelled()) {
                    }

                    JSONObject jsonRoot = new JSONObject(sb.toString());
                    JSONArray dishListJSON = jsonRoot.getJSONArray("dishList");

                    LinkedList<DishModel> dishList = new LinkedList<DishModel>();

                    for (int i = 0; i < dishListJSON.length(); i++) {
                        JSONObject dish = dishListJSON.getJSONObject(i);

                        String name = dish.getString("name");
                        String description = dish.getString("description");
                        String allergens = dish.getString("allergens");
                        float price = Float.parseFloat(dish.getString("price"));
                        String photo = dish.getString("photo");

                        int photoInt = Integer.parseInt(photo);
                        int iconResource = R.drawable.patatas;
                        switch (photoInt) {
                            case 1:
                                iconResource = R.drawable.patatas;
                                break;
                            case 2:
                                iconResource = R.drawable.spaguetti;
                                break;
                            case 3:
                                iconResource = R.drawable.dorada;
                                break;
                            case 4:
                                iconResource = R.drawable.huevos;
                                break;
                            case 5:
                                iconResource = R.drawable.arroz;
                                break;

                        }

                        mDish = new DishModel(name, description, price, iconResource, allergens);
                        dishList.add(mDish);
                    }

                    return dishList;

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected void onPostExecute(LinkedList<DishModel> dishList) {
                super.onPostExecute(dishList);

                if (dishList != null) {
                    setDishList(dishList);
                }
                else {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                    alertDialog.setTitle(R.string.error);
                    alertDialog.setMessage(R.string.error);
                    alertDialog.setPositiveButton("Reintentar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            downloadMainDishList();
                        }
                    });

                    alertDialog.show();
                }
            }
        };

        dishListDownloader.execute(mDish);
    }

    private void setDishList(LinkedList<DishModel> dishList) {
        if (dishList == null) {
            downloadMainDishList();

        } else {
            mViewSwitcher.setDisplayedChild(DISHLIST_VIEW_INDEX);

            mList.setAdapter(new DishListRecyclerViewAdapter(dishList, this));
        }

    }


}
