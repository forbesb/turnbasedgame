/**
 * ServerServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package server;

public class ServerServiceLocator extends org.apache.axis.client.Service implements server.ServerService {

    public ServerServiceLocator() {
    }


    public ServerServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ServerServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BrianSoap
    private java.lang.String BrianSoap_address = "http://10.10.10.33/BrianSoap";

    public java.lang.String getBrianSoapAddress() {
        return BrianSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String BrianSoapWSDDServiceName = "BrianSoap";

    public java.lang.String getBrianSoapWSDDServiceName() {
        return BrianSoapWSDDServiceName;
    }

    public void setBrianSoapWSDDServiceName(java.lang.String name) {
        BrianSoapWSDDServiceName = name;
    }

    public server.Server getBrianSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BrianSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBrianSoap(endpoint);
    }

    public server.Server getBrianSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            server.BrianSoapSoapBindingStub _stub = new server.BrianSoapSoapBindingStub(portAddress, this);
            _stub.setPortName(getBrianSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBrianSoapEndpointAddress(java.lang.String address) {
        BrianSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (server.Server.class.isAssignableFrom(serviceEndpointInterface)) {
                server.BrianSoapSoapBindingStub _stub = new server.BrianSoapSoapBindingStub(new java.net.URL(BrianSoap_address), this);
                _stub.setPortName(getBrianSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("BrianSoap".equals(inputPortName)) {
            return getBrianSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:server", "ServerService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:server", "BrianSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("BrianSoap".equals(portName)) {
            setBrianSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
