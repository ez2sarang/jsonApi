package com.verimatrix.omi;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.7.13
 * 2014-11-20T18:42:57.899+09:00
 * Generated source version: 2.7.13
 * 
 */
@WebService(targetNamespace = "http://www.verimatrix.com/omi", name = "DomainMgmt")
@XmlSeeAlso({com.verimatrix.schemas.iptvtypes.ObjectFactory.class, com.verimatrix.schemas.omitypes.ObjectFactory.class})
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface DomainMgmt {

    @WebResult(name = "queryResult", targetNamespace = "http://www.verimatrix.com/omi", partName = "queryResult")
    @WebMethod(action = "http://www.verimatrix.com/omi/getDomainDeviceList")
    public com.verimatrix.schemas.omitypes.DomainDeviceListResult getDomainDeviceList(
        @WebParam(partName = "domainDeviceListQuery", name = "domainDeviceListQuery")
        com.verimatrix.schemas.omitypes.DomainDeviceListQuery domainDeviceListQuery,
        @WebParam(partName = "sessionHandle", name = "sessionHandle")
        com.verimatrix.schemas.omitypes.SessionHandle sessionHandle
    );

    @WebResult(name = "resultList", targetNamespace = "http://www.verimatrix.com/omi", partName = "resultList")
    @WebMethod(action = "http://www.verimatrix.com/omi/removeDomains")
    public com.verimatrix.schemas.omitypes.ResultList removeDomains(
        @WebParam(partName = "domainList", name = "domainList")
        com.verimatrix.schemas.omitypes.DomainList domainList,
        @WebParam(partName = "sessionHandle", name = "sessionHandle")
        com.verimatrix.schemas.omitypes.SessionHandle sessionHandle
    );

    @WebResult(name = "queryResult", targetNamespace = "http://www.verimatrix.com/omi", partName = "queryResult")
    @WebMethod(action = "http://www.verimatrix.com/omi/getDomainList")
    public com.verimatrix.schemas.omitypes.DomainListResult getDomainList(
        @WebParam(partName = "domainListQuery", name = "domainListQuery")
        com.verimatrix.schemas.omitypes.DomainListQuery domainListQuery,
        @WebParam(partName = "sessionHandle", name = "sessionHandle")
        com.verimatrix.schemas.omitypes.SessionHandle sessionHandle
    );

    @WebResult(name = "resultList", targetNamespace = "http://www.verimatrix.com/omi", partName = "resultList")
    @WebMethod(action = "http://www.verimatrix.com/omi/modifyDomainDevices")
    public com.verimatrix.schemas.omitypes.ResultList modifyDomainDevices(
        @WebParam(partName = "domain", name = "domain")
        com.verimatrix.schemas.omitypes.Domain domain,
        @WebParam(partName = "deviceList", name = "deviceList")
        com.verimatrix.schemas.omitypes.DomainDeviceList deviceList,
        @WebParam(partName = "sessionHandle", name = "sessionHandle")
        com.verimatrix.schemas.omitypes.SessionHandle sessionHandle
    );

    @WebResult(name = "resultList", targetNamespace = "http://www.verimatrix.com/omi", partName = "resultList")
    @WebMethod(action = "http://www.verimatrix.com/omi/removeDomainDevices")
    public com.verimatrix.schemas.omitypes.ResultList removeDomainDevices(
        @WebParam(partName = "domain", name = "domain")
        com.verimatrix.schemas.omitypes.Domain domain,
        @WebParam(partName = "deviceList", name = "deviceList")
        com.verimatrix.schemas.omitypes.DomainDeviceList deviceList,
        @WebParam(partName = "sessionHandle", name = "sessionHandle")
        com.verimatrix.schemas.omitypes.SessionHandle sessionHandle
    );

    @WebResult(name = "queryResult", targetNamespace = "http://www.verimatrix.com/omi", partName = "queryResult")
    @WebMethod(action = "http://www.verimatrix.com/omi/getDeviceDomainList")
    public com.verimatrix.schemas.omitypes.DomainListResult getDeviceDomainList(
        @WebParam(partName = "deviceDomainListQuery", name = "deviceDomainListQuery")
        com.verimatrix.schemas.omitypes.DeviceDomainListQuery deviceDomainListQuery,
        @WebParam(partName = "sessionHandle", name = "sessionHandle")
        com.verimatrix.schemas.omitypes.SessionHandle sessionHandle
    );

    @WebResult(name = "resultList", targetNamespace = "http://www.verimatrix.com/omi", partName = "resultList")
    @WebMethod(action = "http://www.verimatrix.com/omi/addDomains")
    public com.verimatrix.schemas.omitypes.ResultList addDomains(
        @WebParam(partName = "domainList", name = "domainList")
        com.verimatrix.schemas.omitypes.DomainList domainList,
        @WebParam(partName = "sessionHandle", name = "sessionHandle")
        com.verimatrix.schemas.omitypes.SessionHandle sessionHandle
    );

    @WebResult(name = "resultList", targetNamespace = "http://www.verimatrix.com/omi", partName = "resultList")
    @WebMethod(action = "http://www.verimatrix.com/omi/addDomainDevices")
    public com.verimatrix.schemas.omitypes.ResultList addDomainDevices(
        @WebParam(partName = "domain", name = "domain")
        com.verimatrix.schemas.omitypes.Domain domain,
        @WebParam(partName = "deviceList", name = "deviceList")
        com.verimatrix.schemas.omitypes.DomainDeviceList deviceList,
        @WebParam(partName = "sessionHandle", name = "sessionHandle")
        com.verimatrix.schemas.omitypes.SessionHandle sessionHandle
    );

    @WebResult(name = "resultList", targetNamespace = "http://www.verimatrix.com/omi", partName = "resultList")
    @WebMethod(action = "http://www.verimatrix.com/omi/modifyDomains")
    public com.verimatrix.schemas.omitypes.ResultList modifyDomains(
        @WebParam(partName = "domainList", name = "domainList")
        com.verimatrix.schemas.omitypes.DomainList domainList,
        @WebParam(partName = "sessionHandle", name = "sessionHandle")
        com.verimatrix.schemas.omitypes.SessionHandle sessionHandle
    );
}
