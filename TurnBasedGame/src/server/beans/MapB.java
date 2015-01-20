/**
 * MapB.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package server.beans;

public class MapB  implements java.io.Serializable {
    private java.lang.String[] startposa;

    private java.lang.String[] startposb;

    private java.lang.String[][] tiles;

    public MapB() {
    }

    public MapB(
           java.lang.String[] startposa,
           java.lang.String[] startposb,
           java.lang.String[][] tiles) {
           this.startposa = startposa;
           this.startposb = startposb;
           this.tiles = tiles;
    }


    /**
     * Gets the startposa value for this MapB.
     * 
     * @return startposa
     */
    public java.lang.String[] getStartposa() {
        return startposa;
    }


    /**
     * Sets the startposa value for this MapB.
     * 
     * @param startposa
     */
    public void setStartposa(java.lang.String[] startposa) {
        this.startposa = startposa;
    }


    /**
     * Gets the startposb value for this MapB.
     * 
     * @return startposb
     */
    public java.lang.String[] getStartposb() {
        return startposb;
    }


    /**
     * Sets the startposb value for this MapB.
     * 
     * @param startposb
     */
    public void setStartposb(java.lang.String[] startposb) {
        this.startposb = startposb;
    }


    /**
     * Gets the tiles value for this MapB.
     * 
     * @return tiles
     */
    public java.lang.String[][] getTiles() {
        return tiles;
    }


    /**
     * Sets the tiles value for this MapB.
     * 
     * @param tiles
     */
    public void setTiles(java.lang.String[][] tiles) {
        this.tiles = tiles;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MapB)) return false;
        MapB other = (MapB) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.startposa==null && other.getStartposa()==null) || 
             (this.startposa!=null &&
              java.util.Arrays.equals(this.startposa, other.getStartposa()))) &&
            ((this.startposb==null && other.getStartposb()==null) || 
             (this.startposb!=null &&
              java.util.Arrays.equals(this.startposb, other.getStartposb()))) &&
            ((this.tiles==null && other.getTiles()==null) || 
             (this.tiles!=null &&
              java.util.Arrays.equals(this.tiles, other.getTiles())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getStartposa() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getStartposa());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getStartposa(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getStartposb() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getStartposb());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getStartposb(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTiles() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTiles());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTiles(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MapB.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://beans.server", "MapB"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startposa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "startposa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startposb");
        elemField.setXmlName(new javax.xml.namespace.QName("", "startposb"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tiles");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tiles"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
