package com.shinnosuke.net;

import java.io.IOException;
import java.util.ArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/Post")
public class WebSocketService {
	private static ArrayList<Session> sessionList = new ArrayList<Session>();

    @OnOpen
    public void onStart(Session session){
        try{
            sessionList.add(session);
            //asynchronous communication
            session.getBasicRemote().sendText("Hello!");
        }catch(IOException e){}
    }

    @OnClose
    public void onStop(Session session){
        sessionList.remove(session);
    }

    @OnMessage
    public void onMessage(String msg){
        try{
            for(Session session : sessionList){
                //asynchronous communication
                session.getBasicRemote().sendText("Server:"+msg);
            }
        }catch(IOException e){}
    }
}
