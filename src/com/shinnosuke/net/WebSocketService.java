package com.shinnosuke.net;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONException;
import org.json.JSONObject;

import com.shinnosuke.net.beans.RoomMember;

@ServerEndpoint(value="/Post/{roomId}")
public class WebSocketService {
	private static ArrayList<Session> sessionList = new ArrayList<Session>();
	private static Map<Integer,ArrayList<Session>> sessions = new HashMap<Integer, ArrayList<Session>>();

	private static RoomMember rm = new RoomMember();
//	RoomMember rm = new RoomMember();

    @OnOpen
    public void onStart(Session session, @PathParam("roomId") String roomId){
        try{
//            sessionList.add(session);
        	rm.setMemberList(roomId, session);

        	Date date = new Date();
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");

        	JSONObject json = new JSONObject();
        	JsonEscape escJson = new JsonEscape();
        	json.put("userId", "BestOwner");
			json.put("userName", "運営者");
			json.put("messeage", "ゲストが入室しました。");
			json.put("date", sdf.format(date));

            session.getBasicRemote().sendText(escJson.getString(json));
        }catch(IOException e){
        	e.printStackTrace();
        } catch (JSONException e) {
			e.printStackTrace();
		}
    }

    @OnClose
    public void onStop(Session session, @PathParam("roomId") String roomId){
        sessionList.remove(session);
    }

    @OnMessage
    public void onMessage(String msg, Session getSession, @PathParam("roomId") String roomId){
//    	Set<Session> sessions = getSession.getOpenSessions();
    	ArrayList<Session> sessions = rm.getMemberList(roomId);
        try{
//            for(Session session : sessionList){
        	for(Session session : sessions){
                //asynchronous communication
                session.getBasicRemote().sendText(msg);
            }
        }catch(IOException e){
        	e.printStackTrace();
        }
    }
}
