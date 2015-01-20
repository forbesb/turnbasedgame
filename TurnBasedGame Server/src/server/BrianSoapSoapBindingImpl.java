/**
 * BrianSoapSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package server;

public class BrianSoapSoapBindingImpl implements server.Server{
	ServerImplementation serv = new ServerImplementation();
    public void initializeSearch(java.lang.String key, server.beans.Team t) throws java.rmi.RemoteException {
    	serv.initializeSearch(key, t);
    }

    public boolean actionPossible(java.lang.String action, java.lang.String token) throws java.rmi.RemoteException {
        return serv.actionPossible(action, token);
    }

    public java.lang.String login(java.lang.String name, byte[] password) throws java.rmi.RemoteException {
        return serv.login(name, password);
    }

    public server.beans.Team initBattle(java.lang.String key) throws java.rmi.RemoteException {
        return serv.initBattle(key);
    }

    public server.beans.Team getTeam(java.lang.String username) throws java.rmi.RemoteException {
        return serv.getTeam(username);
    }

    public boolean hasOpponent(java.lang.String key) throws java.rmi.RemoteException {
        return serv.hasOpponent(key);
    }

    public boolean createAccount(java.lang.String name, byte[] password) throws java.rmi.RemoteException {
        return serv.createAccount(name, password);
    }

    public boolean sendTeam(server.beans.Team t, java.lang.String key) throws java.rmi.RemoteException {
        return serv.sendTeam(t, key);
    }

    public boolean hasNewMoves(java.lang.String token) throws java.rmi.RemoteException {
        return serv.hasNewMoves(token);
    }

    public java.lang.String getNextAction(java.lang.String token) throws java.rmi.RemoteException {
        return serv.getNextAction(token);
    }

    public boolean sendAction(java.lang.String action, java.lang.String token) throws java.rmi.RemoteException {
        return serv.sendAction(action, token);
    }

    public server.beans.MapB getMap(java.lang.String key) throws java.rmi.RemoteException {
        return serv.getMap(key);
    }

    public server.beans.Profile getProfile(java.lang.String username) throws java.rmi.RemoteException {
        return serv.getProfile(username);
    }

}
