package com.shinnosuke.net.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.Session;

public class RoomMember {
	private Map<String, ArrayList<Session>> allList = new HashMap<String, ArrayList<Session>>();
//	private static Map<String, ArrayList<Session>> allList = new HashMap<String, ArrayList<Session>>();

	public ArrayList<Session> getMemberList(String roomId) {
		return allList.get(roomId);
	}

	public void setMemberList(String roomId, Session sessionId) {
		if (allList.containsKey(roomId)) {
			allList.get(roomId).add(sessionId);
		} else {
			ArrayList<Session> addSession = new ArrayList<Session>();
			addSession.add(sessionId);
			allList.put(roomId, addSession);
		}
	}
}
