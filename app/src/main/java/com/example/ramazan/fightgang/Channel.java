package com.example.ramazan.fightgang;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by ramazan on 12.05.2016.
 */
public class Channel {

    String ApiToken="p0x0XirrQV66w18372t8l91WCklUqq4K657tT1A06b6m10TuG6n6894S2IHFH3YP";

    String RegisterUrl="http://private-anon-ca62aa4b7-fightgang.apiary-mock.com/players";
    String LoginUrl="http://private-anon-ca62aa4b7-fightgang.apiary-mock.com/players/meto";
    String ArenaUrl="http://private-anon-ca62aa4b7-fightgang.apiary-mock.com/arena";

    String ProfileUrl="http://private-anon-ca62aa4b7-fightgang.apiary-mock.com/players/me";

    String AttackUrl="http://private-anon-ca62aa4b7-fightgang.apiary-mock.com/arena/attack/:id";
    String ChatUrl="http://private-anon-ca62aa4b7-fightgang.apiary-mock.com/chat";

    public Channel(){}

    public String HttpPostRegister(String UserName,String password,String Alias) {

        InputStream inputStream = null;
        String result = "";
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost post=new HttpPost(RegisterUrl);
            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
            nameValuePair.add(new BasicNameValuePair("name", UserName));
            nameValuePair.add(new BasicNameValuePair("alias", Alias));
            nameValuePair.add(new BasicNameValuePair("password", password));

            UrlEncodedFormEntity ent = new UrlEncodedFormEntity(nameValuePair, HTTP.UTF_8);
            ent.setContentType("application/json");
            post.addHeader("X-Api-Token",ApiToken);
            post.setEntity(ent);


            // make post request to the given URL
            HttpResponse httpResponse = httpclient.execute(post);

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Data receipt fail";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
        return result;
    }

    public String HttpPostAttack() {

        InputStream inputStream = null;
        String result = "";
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost post=new HttpPost(AttackUrl);

            post.addHeader("X-Api-Token", ApiToken);
            post.addHeader("Authorization", "Basic Um9iZXJ0UGF1bHNvbjoxMjM0NTY3OA==");

            // make post request to the given URL
            HttpResponse httpResponse = httpclient.execute(post);

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Data receipt fail";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
        return result;
    }

    public String HttpGetArena() {

        InputStream inputStream = null;
        String result = "";
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet httpGet=new HttpGet(ArenaUrl);

            httpGet.addHeader("Authorization", "Basic Um9iZXJ0UGF1bHNvbjoxMjM0NTY3OA==");
            httpGet.addHeader("X-Api-Token", ApiToken);

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpGet);

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Data receipt fail";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
        return result;
    }

    public String HttpGetProfile() {

        InputStream inputStream = null;
        String result = "";
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet httpGet=new HttpGet(ProfileUrl);

            httpGet.addHeader("Authorization", "Basic Um9iZXJ0UGF1bHNvbjoxMjM0NTY3OA==");
            httpGet.addHeader("X-Api-Token", ApiToken);

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpGet);

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Data receipt fail";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
        return result;
    }

    public String HttpPostLogin(String UserName,String password) {

        InputStream inputStream = null;
        String result = "";
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost post=new HttpPost(LoginUrl);
            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
            nameValuePair.add(new BasicNameValuePair("name", UserName));
            nameValuePair.add(new BasicNameValuePair("password", password));

            UrlEncodedFormEntity ent = new UrlEncodedFormEntity(nameValuePair, HTTP.UTF_8);
            ent.setContentType("application/json");
            post.addHeader("X-Api-Token",ApiToken);
            post.setEntity(ent);


            // make post request to the given URL
            HttpResponse httpResponse = httpclient.execute(post);

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Data receipt fail";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
        return result;
    }

    public String HttpPostEnterArena() {

        InputStream inputStream = null;
        String result = "";
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost post=new HttpPost(ArenaUrl);

            post.addHeader("X-Api-Token", ApiToken);
            post.addHeader("Authorization", "Basic Um9iZXJ0UGF1bHNvbjoxMjM0NTY3OA==");

            // make post request to the given URL
            HttpResponse httpResponse = httpclient.execute(post);

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Data receipt fail";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
        return result;
    }

    public String HttpDeleteLeaveArena() {

        InputStream inputStream = null;
        String result = "";
        try {
            HttpClient httpclient = new DefaultHttpClient();

            HttpDelete httpDelete=new HttpDelete(ArenaUrl);

            httpDelete.addHeader("X-Api-Token", ApiToken);
            httpDelete.addHeader("Authorization", "Basic Um9iZXJ0UGF1bHNvbjoxMjM0NTY3OA==");

            // make post request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpDelete);

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Data receipt fail";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
        return result;
    }

    public String HttpGetChats() {

        InputStream inputStream = null;
        String result = "";
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet httpGet=new HttpGet(ChatUrl);

            httpGet.addHeader("Authorization", "Basic Um9iZXJ0UGF1bHNvbjoxMjM0NTY3OA==");
            httpGet.addHeader("X-Api-Token", ApiToken);

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpGet);

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Data receipt fail";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
        return result;
    }

    public String HttpPostSendMessage(String message) {

        InputStream inputStream = null;
        String result = "";
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost post=new HttpPost(ChatUrl);

            post.addHeader("X-Api-Token", ApiToken);
            post.addHeader("Authorization", "Basic Um9iZXJ0UGF1bHNvbjoxMjM0NTY3OA==");

            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
            nameValuePair.add(new BasicNameValuePair("message", message));
            UrlEncodedFormEntity ent = new UrlEncodedFormEntity(nameValuePair, HTTP.UTF_8);

            ent.setContentType("application/json");
            post.setEntity(ent);

            // make post request to the given URL
            HttpResponse httpResponse = httpclient.execute(post);

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Data receipt fail";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
        return result;
    }

    public  String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;
    }

    public UserModel getUser(String data) {
        UserModel model=new UserModel();

        int id;
        String alias;
        int exp;
        int level;
        int expNet;
        int hits;
        int stamina;
        int maxStamina;
        try {
            JSONObject post = (new JSONObject(data));
            id=post.getInt("id");
            alias=post.getString("alias");
            exp=post.getInt("exp");
            level=post.getInt("level");
            expNet=post.getInt("expNext");
            hits=post.getInt("hits");
            stamina=post.getInt("stamina");
            maxStamina=post.getInt("maxStamina");
            model.setId(id);
            model.setAlias(alias);
            model.setExp(exp);
            model.setLevel(level);
            model.setExpNet(expNet);
            model.setHits(hits);
            model.setStamina(stamina);
            model.setMaxStamina(maxStamina);

        }
        catch (JSONException e){
            e.printStackTrace();}
        return model;
    }

    public List<UserModel> getUserList(String data) {
        List<UserModel> modelList=new ArrayList<UserModel>();


        int id;
        String alias;
        int exp;
        int level;
        int expNet;
        int hits;
        int stamina;
        int maxStamina;
        try {
            //JSONObject post = (new JSONObject(data));

            JSONArray List = new JSONArray(data);

            for (int i = 0; i < List.length(); i++) {

                UserModel model=new UserModel();
                JSONObject o = List.getJSONObject(i);

                id=o.getInt("id");
                alias=o.getString("alias");
                exp=o.getInt("exp");
                level=o.getInt("level");
                expNet=o.getInt("expNext");
                hits=o.getInt("hits");
                stamina=o.getInt("stamina");
                maxStamina=o.getInt("maxStamina");
                model.setId(id);
                model.setAlias(alias);
                model.setExp(exp);
                model.setLevel(level);
                model.setExpNet(expNet);
                model.setHits(hits);
                model.setStamina(stamina);
                model.setMaxStamina(maxStamina);
                modelList.add(model);
            }


        }
        catch (JSONException e){
            e.printStackTrace();}
        return modelList;
    }

    public String[] getAttackData(String data) {
        String[] attackData=new  String[2];

        String damage;
        String DefenderAlias;

        try {
            JSONObject post = (new JSONObject(data));

            damage=post.getString("damage");
            JSONObject defenderobj=post.getJSONObject("defender");
            DefenderAlias=defenderobj.getString("alias");
            attackData[0]=DefenderAlias;
            attackData[1]=damage;

        }
        catch (JSONException e){
            e.printStackTrace();}
        return attackData;
    }

    public List<ChatModel> getChatList(String data) {

        List<ChatModel> modelList=new ArrayList<ChatModel>();

        long unixSeconds;
        String from;
        String message;

        try {
            JSONArray List = new JSONArray(data);

            for (int i = 0; i < List.length(); i++) {

                ChatModel model=new ChatModel();
                JSONObject o = List.getJSONObject(i);

                unixSeconds=o.getLong("timestamp");
                Date date = new Date(unixSeconds*1000L);
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                sdf.setTimeZone(TimeZone.getTimeZone("GMT+1"));
                String formattedDate = sdf.format(date);

                from=o.getString("from");
                message=o.getString("message");

                model.setFrom(from);
                model.setMessage(message);
                model.setDate(formattedDate);
                modelList.add(model);
            }


        }
        catch (JSONException e){
            e.printStackTrace();}
        return modelList;
    }

    public ChatModel getChatMessage(String data) {

        ChatModel model=new ChatModel();

        long unixSeconds;
        String from;
        String message;

        try {
                JSONObject o = new JSONObject(data);

                unixSeconds=o.getLong("timestamp");

                Date date = new Date(unixSeconds*1000L);
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                sdf.setTimeZone(TimeZone.getTimeZone("GMT+1"));
                String formattedDate = sdf.format(date);

                from=o.getString("from");
                message=o.getString("message");

                model.setFrom(from);
                model.setMessage(message);
                model.setDate(formattedDate);
        }
        catch (JSONException e){
            e.printStackTrace();}
        return model;
    }

}
