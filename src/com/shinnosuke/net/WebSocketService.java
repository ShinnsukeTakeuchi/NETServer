package com.shinnosuke.net;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/Post")
public class WebSocketService {
	private static ArrayList<Session> sessionList = new ArrayList<Session>();
	private static Map<Integer,ArrayList<Session>> sessions = new HashMap<Integer, ArrayList<Session>>();

    @OnOpen
    public void onStart(Session session){
        try{
            sessionList.add(session);
//            sessions.get(1).add(session);
            //asynchronous communication
            session.getBasicRemote().sendText("〇〇さんが入室しました。");
        }catch(IOException e){}
    }

    @OnClose
    public void onStop(Session session){
        sessionList.remove(session);
    }

    @OnMessage
    public void onMessage(String msg, Session getSession){
    	Set<Session> sessions = getSession.getOpenSessions();
        try{
//            for(Session session : sessionList){
        	for(Session session : sessions){
                //asynchronous communication
                session.getBasicRemote().sendText(msg);
            }
        }catch(IOException e){}
    }
}
