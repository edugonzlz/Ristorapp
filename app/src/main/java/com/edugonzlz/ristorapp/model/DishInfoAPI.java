package com.edugonzlz.ristorapp.model;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;

import com.edugonzlz.ristorapp.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;

import static android.R.attr.max;

/**
 * Created by Edu on 10/12/16.
 */

public class DishInfoAPI {

    private DishModel mDish;
    private LinkedList<DishModel> mMainDishList;

    public DishInfoAPI() {
    }

    public LinkedList<DishModel> downloadMainDishList() {
        AsyncTask<DishModel, Integer, LinkedList<DishModel>> dishListDownloader = new AsyncTask<DishModel, Integer, LinkedList<DishModel>>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                // Mostramos la vista de loading
//                mViewSwitcher.setDisplayedChild(LOADING_VIEW_INDEX);
            }

            @Override
            protected LinkedList<DishModel> doInBackground(DishModel... dishModels) {
                URL url = null;
                InputStream input = null;

                try {
                    url = new URL(String.format("http://www.mocky.io/v2/5843fce1110000c8000e6b0f"));
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

                        photo = photo.substring(0, photo.length() - 1);
                        int photoInt = Integer.parseInt(photo);
                        int iconResource = Integer.parseInt(null);
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

                    mMainDishList = dishList;

                    return mMainDishList;

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

                }
                else {
                    // Ha habido algún error, se lo notificamos al usuario
//                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
//                    alertDialog.setTitle(R.string.error);
//                    alertDialog.setMessage(R.string.error);
//                    alertDialog.setPositiveButton("Reintentar", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            downloadMainDishList();
//                        }
//                    });
//
//                    alertDialog.show();
                }
            }
        };

        dishListDownloader.execute(mDish);
        return mMainDishList;
    }

}
