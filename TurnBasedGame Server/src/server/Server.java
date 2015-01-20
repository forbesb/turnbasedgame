/**
 * Server.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package server;

public interface Server extends java.rmi.Remote {
    public void initializeSearch(java.lang.String key, server.beans.Team t) throws java.rmi.RemoteException;
    public boolean actionPossible(java.lang.String action, java.lang.String token) throws java.rmi.RemoteException;
    public java.lang.String login(java.lang.String name, byte[] password) throws java.rmi.RemoteException;
    public server.beans.Team initBattle(java.lang.String key) throws java.rmi.RemoteException;
    public server.beans.Team getTeam(java.lang.String username) throws java.rmi.RemoteException;
    public boolean hasOpponent(java.lang.String key) throws java.rmi.RemoteException;
    public boolean createAccount(java.lang.String name, byte[] password) throws java.rmi.RemoteException;
    public boolean sendTeam(server.beans.Team t, java.lang.String key) throws java.rmi.RemoteException;
    public boolean hasNewMoves(java.lang.String token) throws java.rmi.RemoteException;
    public java.lang.String getNextAction(java.lang.String token) throws java.rmi.RemoteException;
    public boolean sendAction(java.lang.String action, java.lang.String token) throws java.rmi.RemoteException;
    public server.beans.MapB getMap(java.lang.String key) throws java.rmi.RemoteException;
    public server.beans.Profile getProfile(java.lang.String username) throws java.rmi.RemoteException;
}
