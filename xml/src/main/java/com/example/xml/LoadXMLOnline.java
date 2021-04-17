package com.example.xml;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class LoadXMLOnline extends AsyncTask<String, Void, String> {

    private MainActivity activity = null;
    public LoadXMLOnline(Activity _activity){
        this.activity = (MainActivity) _activity;
    }

    @Override
    protected String doInBackground(String... strings) {
        StringBuilder content = new StringBuilder();

        // Read xml file from website
        content = ReadXMLFromWeb(strings[0]);

        return content.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        // Read xml format
        XMLDOMParser xmldomParser = new XMLDOMParser();

        Document document = xmldomParser.getDocument(s);

        // Read all element [VersionInfo] trong xml file
        NodeList nodeList = document.getElementsByTagName("VersionInfo");

        String version = null;
        for (int i = 0; i < nodeList.getLength(); i++){
            Element element = (Element) nodeList.item(i);

            // Read element [Version] bên trong element [VersionInfo]
            version = xmldomParser.getValue(element, "Version");

            // Set version info cho list data
            activity.arrayList.add(version);
        }
        // Cập nhật lại dữ liệu trong adapter
        activity.adapter.notifyDataSetChanged();

        Toast.makeText(activity, version,Toast.LENGTH_SHORT).show();
    }

    private StringBuilder ReadXMLFromWeb(String... listUrl){
        URL url = null;
        StringBuilder content = new StringBuilder();
        try {
            url = new URL(listUrl[0]);

            InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = null;

            while ((line = bufferedReader.readLine()) != null){
                content.append(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }
}
