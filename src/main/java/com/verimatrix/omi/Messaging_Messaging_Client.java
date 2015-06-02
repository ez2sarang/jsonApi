
package com.verimatrix.omi;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.7.13
 * 2014-11-20T18:42:57.602+09:00
 * Generated source version: 2.7.13
 * 
 */
public final class Messaging_Messaging_Client {

    private static final QName SERVICE_NAME = new QName("http://www.verimatrix.com/omi", "MessagingService");

    private Messaging_Messaging_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = MessagingService.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        MessagingService ss = new MessagingService(wsdlURL, SERVICE_NAME);
        Messaging port = ss.getMessaging();  
        
        {
        System.out.println("Invoking generateDeviceCommand...");
        com.verimatrix.schemas.omitypes.MessageEntity _generateDeviceCommand_messageEntity = new com.verimatrix.schemas.omitypes.MessageEntity();
        com.verimatrix.schemas.omitypes.MessageEntityType _generateDeviceCommand_messageEntityEntityType = com.verimatrix.schemas.omitypes.MessageEntityType.GLOBAL;
        _generateDeviceCommand_messageEntity.setEntityType(_generateDeviceCommand_messageEntityEntityType);
        _generateDeviceCommand_messageEntity.setEntityId("EntityId-1869362189");
        _generateDeviceCommand_messageEntity.setSmsNetworkId("SmsNetworkId-1615535024");
        com.verimatrix.schemas.omitypes.DeviceCommand _generateDeviceCommand_command = new com.verimatrix.schemas.omitypes.DeviceCommand();
        byte[] _generateDeviceCommand_commandCommand = new byte[] {};
        _generateDeviceCommand_command.setCommand(_generateDeviceCommand_commandCommand);
        com.verimatrix.schemas.omitypes.SessionHandle _generateDeviceCommand_sessionHandle = new com.verimatrix.schemas.omitypes.SessionHandle();
        _generateDeviceCommand_sessionHandle.setHandle("Handle-1656039816");
        com.verimatrix.schemas.omitypes.Result _generateDeviceCommand__return = port.generateDeviceCommand(_generateDeviceCommand_messageEntity, _generateDeviceCommand_command, _generateDeviceCommand_sessionHandle);
        System.out.println("generateDeviceCommand.result=" + _generateDeviceCommand__return);


        }
        {
        System.out.println("Invoking generatePreloadedOSM...");
        com.verimatrix.schemas.omitypes.MessageEntity _generatePreloadedOSM_messageEntity = new com.verimatrix.schemas.omitypes.MessageEntity();
        com.verimatrix.schemas.omitypes.MessageEntityType _generatePreloadedOSM_messageEntityEntityType = com.verimatrix.schemas.omitypes.MessageEntityType.GLOBAL;
        _generatePreloadedOSM_messageEntity.setEntityType(_generatePreloadedOSM_messageEntityEntityType);
        _generatePreloadedOSM_messageEntity.setEntityId("EntityId1000218113");
        _generatePreloadedOSM_messageEntity.setSmsNetworkId("SmsNetworkId-1271673037");
        com.verimatrix.schemas.omitypes.PreloadedOnScreenMessage _generatePreloadedOSM_message = new com.verimatrix.schemas.omitypes.PreloadedOnScreenMessage();
        _generatePreloadedOSM_message.setTextId(1883451350);
        _generatePreloadedOSM_message.setDisplayDuration(1566434185);
        com.verimatrix.schemas.omitypes.MessageHandle _generatePreloadedOSM_messageMessageHandle = new com.verimatrix.schemas.omitypes.MessageHandle();
        _generatePreloadedOSM_messageMessageHandle.setSector(875690818);
        _generatePreloadedOSM_messageMessageHandle.setMessageId(1449654864);
        _generatePreloadedOSM_message.setMessageHandle(_generatePreloadedOSM_messageMessageHandle);
        com.verimatrix.schemas.omitypes.MessageAttributes _generatePreloadedOSM_messageMessageAttributes = new com.verimatrix.schemas.omitypes.MessageAttributes();
        com.verimatrix.schemas.omitypes.MessageContentMode _generatePreloadedOSM_messageMessageAttributesContentMode = com.verimatrix.schemas.omitypes.MessageContentMode.NEG;
        _generatePreloadedOSM_messageMessageAttributes.setContentMode(_generatePreloadedOSM_messageMessageAttributesContentMode);
        _generatePreloadedOSM_message.setMessageAttributes(_generatePreloadedOSM_messageMessageAttributes);
        com.verimatrix.schemas.omitypes.MessageQuadrant _generatePreloadedOSM_messageMessageQuadrant = com.verimatrix.schemas.omitypes.MessageQuadrant.NORTH_EAST;
        _generatePreloadedOSM_message.setMessageQuadrant(_generatePreloadedOSM_messageMessageQuadrant);
        com.verimatrix.schemas.omitypes.SessionHandle _generatePreloadedOSM_sessionHandle = new com.verimatrix.schemas.omitypes.SessionHandle();
        _generatePreloadedOSM_sessionHandle.setHandle("Handle-896059129");
        com.verimatrix.schemas.omitypes.Result _generatePreloadedOSM__return = port.generatePreloadedOSM(_generatePreloadedOSM_messageEntity, _generatePreloadedOSM_message, _generatePreloadedOSM_sessionHandle);
        System.out.println("generatePreloadedOSM.result=" + _generatePreloadedOSM__return);


        }
        {
        System.out.println("Invoking preloadMessage...");
        com.verimatrix.schemas.omitypes.MessageEntity _preloadMessage_messageEntity = new com.verimatrix.schemas.omitypes.MessageEntity();
        com.verimatrix.schemas.omitypes.MessageEntityType _preloadMessage_messageEntityEntityType = com.verimatrix.schemas.omitypes.MessageEntityType.GLOBAL;
        _preloadMessage_messageEntity.setEntityType(_preloadMessage_messageEntityEntityType);
        _preloadMessage_messageEntity.setEntityId("EntityId-2134020581");
        _preloadMessage_messageEntity.setSmsNetworkId("SmsNetworkId-1469588745");
        com.verimatrix.schemas.omitypes.PreloadedMessage _preloadMessage_message = new com.verimatrix.schemas.omitypes.PreloadedMessage();
        _preloadMessage_message.setTextId(-596425847);
        _preloadMessage_message.setSector(1739113272);
        com.verimatrix.schemas.omitypes.MessageAttributes _preloadMessage_messageMessageAttributes = new com.verimatrix.schemas.omitypes.MessageAttributes();
        com.verimatrix.schemas.omitypes.MessageContentMode _preloadMessage_messageMessageAttributesContentMode = com.verimatrix.schemas.omitypes.MessageContentMode.NEG;
        _preloadMessage_messageMessageAttributes.setContentMode(_preloadMessage_messageMessageAttributesContentMode);
        _preloadMessage_message.setMessageAttributes(_preloadMessage_messageMessageAttributes);
        com.verimatrix.schemas.omitypes.MessageText _preloadMessage_messageMessageText = new com.verimatrix.schemas.omitypes.MessageText();
        _preloadMessage_messageMessageText.setText("Text716869636");
        _preloadMessage_message.setMessageText(_preloadMessage_messageMessageText);
        com.verimatrix.schemas.omitypes.SessionHandle _preloadMessage_sessionHandle = new com.verimatrix.schemas.omitypes.SessionHandle();
        _preloadMessage_sessionHandle.setHandle("Handle-1809752803");
        com.verimatrix.schemas.omitypes.Result _preloadMessage__return = port.preloadMessage(_preloadMessage_messageEntity, _preloadMessage_message, _preloadMessage_sessionHandle);
        System.out.println("preloadMessage.result=" + _preloadMessage__return);


        }
        {
        System.out.println("Invoking deleteOSM...");
        com.verimatrix.schemas.omitypes.MessageEntity _deleteOSM_messageEntity = new com.verimatrix.schemas.omitypes.MessageEntity();
        com.verimatrix.schemas.omitypes.MessageEntityType _deleteOSM_messageEntityEntityType = com.verimatrix.schemas.omitypes.MessageEntityType.GLOBAL;
        _deleteOSM_messageEntity.setEntityType(_deleteOSM_messageEntityEntityType);
        _deleteOSM_messageEntity.setEntityId("EntityId-1684151010");
        _deleteOSM_messageEntity.setSmsNetworkId("SmsNetworkId-554192130");
        com.verimatrix.schemas.omitypes.MessageHandle _deleteOSM_message = new com.verimatrix.schemas.omitypes.MessageHandle();
        _deleteOSM_message.setSector(-437052251);
        _deleteOSM_message.setMessageId(-511901770);
        com.verimatrix.schemas.omitypes.SessionHandle _deleteOSM_sessionHandle = new com.verimatrix.schemas.omitypes.SessionHandle();
        _deleteOSM_sessionHandle.setHandle("Handle-104495885");
        com.verimatrix.schemas.omitypes.Result _deleteOSM__return = port.deleteOSM(_deleteOSM_messageEntity, _deleteOSM_message, _deleteOSM_sessionHandle);
        System.out.println("deleteOSM.result=" + _deleteOSM__return);


        }
        {
        System.out.println("Invoking generateOSM...");
        com.verimatrix.schemas.omitypes.MessageEntity _generateOSM_messageEntity = new com.verimatrix.schemas.omitypes.MessageEntity();
        com.verimatrix.schemas.omitypes.MessageEntityType _generateOSM_messageEntityEntityType = com.verimatrix.schemas.omitypes.MessageEntityType.DOMAIN;
        _generateOSM_messageEntity.setEntityType(_generateOSM_messageEntityEntityType);
        _generateOSM_messageEntity.setEntityId("EntityId2018893641");
        _generateOSM_messageEntity.setSmsNetworkId("SmsNetworkId977072745");
        com.verimatrix.schemas.omitypes.OnScreenMessage _generateOSM_message = new com.verimatrix.schemas.omitypes.OnScreenMessage();
        com.verimatrix.schemas.omitypes.MessageHandle _generateOSM_messageMessageHandle = new com.verimatrix.schemas.omitypes.MessageHandle();
        _generateOSM_messageMessageHandle.setSector(862369495);
        _generateOSM_messageMessageHandle.setMessageId(242342861);
        _generateOSM_message.setMessageHandle(_generateOSM_messageMessageHandle);
        com.verimatrix.schemas.omitypes.MessageAttributes _generateOSM_messageMessageAttributes = new com.verimatrix.schemas.omitypes.MessageAttributes();
        com.verimatrix.schemas.omitypes.MessageContentMode _generateOSM_messageMessageAttributesContentMode = com.verimatrix.schemas.omitypes.MessageContentMode.POS;
        _generateOSM_messageMessageAttributes.setContentMode(_generateOSM_messageMessageAttributesContentMode);
        _generateOSM_message.setMessageAttributes(_generateOSM_messageMessageAttributes);
        java.util.List<com.verimatrix.schemas.omitypes.MessageText> _generateOSM_messageMessageText = new java.util.ArrayList<com.verimatrix.schemas.omitypes.MessageText>();
        com.verimatrix.schemas.omitypes.MessageText _generateOSM_messageMessageTextVal1 = new com.verimatrix.schemas.omitypes.MessageText();
        _generateOSM_messageMessageTextVal1.setText("Text1654950495");
        _generateOSM_messageMessageText.add(_generateOSM_messageMessageTextVal1);
        _generateOSM_message.getMessageText().addAll(_generateOSM_messageMessageText);
        java.util.List<com.verimatrix.schemas.omitypes.MessageDescriptor> _generateOSM_messageMessageDescriptor = new java.util.ArrayList<com.verimatrix.schemas.omitypes.MessageDescriptor>();
        com.verimatrix.schemas.omitypes.MessageDescriptor _generateOSM_messageMessageDescriptorVal1 = new com.verimatrix.schemas.omitypes.MessageDescriptor();
        com.verimatrix.schemas.omitypes.MessageDisplayMode _generateOSM_messageMessageDescriptorVal1DisplayMode = com.verimatrix.schemas.omitypes.MessageDisplayMode.FX_STILLCA;
        _generateOSM_messageMessageDescriptorVal1.setDisplayMode(_generateOSM_messageMessageDescriptorVal1DisplayMode);
        _generateOSM_messageMessageDescriptorVal1.setDisplayDuration(564972812);
        com.verimatrix.schemas.omitypes.MessageOrigin _generateOSM_messageMessageDescriptorVal1XOrigin = com.verimatrix.schemas.omitypes.MessageOrigin.ORIGR;
        _generateOSM_messageMessageDescriptorVal1.setXOrigin(_generateOSM_messageMessageDescriptorVal1XOrigin);
        _generateOSM_messageMessageDescriptorVal1.setXPosition(-1745537798);
        com.verimatrix.schemas.omitypes.MessageAnchor _generateOSM_messageMessageDescriptorVal1XAnchor = com.verimatrix.schemas.omitypes.MessageAnchor.AUTOTD;
        _generateOSM_messageMessageDescriptorVal1.setXAnchor(_generateOSM_messageMessageDescriptorVal1XAnchor);
        com.verimatrix.schemas.omitypes.MessageOrigin _generateOSM_messageMessageDescriptorVal1YOrigin = com.verimatrix.schemas.omitypes.MessageOrigin.ORIGDWN;
        _generateOSM_messageMessageDescriptorVal1.setYOrigin(_generateOSM_messageMessageDescriptorVal1YOrigin);
        _generateOSM_messageMessageDescriptorVal1.setYPosition(-1337001453);
        com.verimatrix.schemas.omitypes.MessageAnchor _generateOSM_messageMessageDescriptorVal1YAnchor = com.verimatrix.schemas.omitypes.MessageAnchor.AUTOTD;
        _generateOSM_messageMessageDescriptorVal1.setYAnchor(_generateOSM_messageMessageDescriptorVal1YAnchor);
        com.verimatrix.schemas.omitypes.MessageAlign _generateOSM_messageMessageDescriptorVal1TextAlign = com.verimatrix.schemas.omitypes.MessageAlign.RIGHT;
        _generateOSM_messageMessageDescriptorVal1.setTextAlign(_generateOSM_messageMessageDescriptorVal1TextAlign);
        _generateOSM_messageMessageDescriptorVal1.setBgColor(282909590);
        _generateOSM_messageMessageDescriptorVal1.setAlpha(1031045);
        _generateOSM_messageMessageDescriptor.add(_generateOSM_messageMessageDescriptorVal1);
        _generateOSM_message.getMessageDescriptor().addAll(_generateOSM_messageMessageDescriptor);
        com.verimatrix.schemas.omitypes.SessionHandle _generateOSM_sessionHandle = new com.verimatrix.schemas.omitypes.SessionHandle();
        _generateOSM_sessionHandle.setHandle("Handle193223959");
        com.verimatrix.schemas.omitypes.Result _generateOSM__return = port.generateOSM(_generateOSM_messageEntity, _generateOSM_message, _generateOSM_sessionHandle);
        System.out.println("generateOSM.result=" + _generateOSM__return);


        }
        {
        System.out.println("Invoking diagnoseOSM...");
        com.verimatrix.schemas.omitypes.MessageEntity _diagnoseOSM_messageEntity = new com.verimatrix.schemas.omitypes.MessageEntity();
        com.verimatrix.schemas.omitypes.MessageEntityType _diagnoseOSM_messageEntityEntityType = com.verimatrix.schemas.omitypes.MessageEntityType.GLOBAL;
        _diagnoseOSM_messageEntity.setEntityType(_diagnoseOSM_messageEntityEntityType);
        _diagnoseOSM_messageEntity.setEntityId("EntityId248416978");
        _diagnoseOSM_messageEntity.setSmsNetworkId("SmsNetworkId-487516446");
        com.verimatrix.schemas.omitypes.OnScreenMessage _diagnoseOSM_message = new com.verimatrix.schemas.omitypes.OnScreenMessage();
        com.verimatrix.schemas.omitypes.MessageHandle _diagnoseOSM_messageMessageHandle = new com.verimatrix.schemas.omitypes.MessageHandle();
        _diagnoseOSM_messageMessageHandle.setSector(-993453833);
        _diagnoseOSM_messageMessageHandle.setMessageId(2096300181);
        _diagnoseOSM_message.setMessageHandle(_diagnoseOSM_messageMessageHandle);
        com.verimatrix.schemas.omitypes.MessageAttributes _diagnoseOSM_messageMessageAttributes = new com.verimatrix.schemas.omitypes.MessageAttributes();
        com.verimatrix.schemas.omitypes.MessageContentMode _diagnoseOSM_messageMessageAttributesContentMode = com.verimatrix.schemas.omitypes.MessageContentMode.POS;
        _diagnoseOSM_messageMessageAttributes.setContentMode(_diagnoseOSM_messageMessageAttributesContentMode);
        _diagnoseOSM_message.setMessageAttributes(_diagnoseOSM_messageMessageAttributes);
        java.util.List<com.verimatrix.schemas.omitypes.MessageText> _diagnoseOSM_messageMessageText = new java.util.ArrayList<com.verimatrix.schemas.omitypes.MessageText>();
        com.verimatrix.schemas.omitypes.MessageText _diagnoseOSM_messageMessageTextVal1 = new com.verimatrix.schemas.omitypes.MessageText();
        _diagnoseOSM_messageMessageTextVal1.setText("Text-484822874");
        _diagnoseOSM_messageMessageText.add(_diagnoseOSM_messageMessageTextVal1);
        _diagnoseOSM_message.getMessageText().addAll(_diagnoseOSM_messageMessageText);
        java.util.List<com.verimatrix.schemas.omitypes.MessageDescriptor> _diagnoseOSM_messageMessageDescriptor = new java.util.ArrayList<com.verimatrix.schemas.omitypes.MessageDescriptor>();
        com.verimatrix.schemas.omitypes.MessageDescriptor _diagnoseOSM_messageMessageDescriptorVal1 = new com.verimatrix.schemas.omitypes.MessageDescriptor();
        com.verimatrix.schemas.omitypes.MessageDisplayMode _diagnoseOSM_messageMessageDescriptorVal1DisplayMode = com.verimatrix.schemas.omitypes.MessageDisplayMode.RM_NEXTCA;
        _diagnoseOSM_messageMessageDescriptorVal1.setDisplayMode(_diagnoseOSM_messageMessageDescriptorVal1DisplayMode);
        _diagnoseOSM_messageMessageDescriptorVal1.setDisplayDuration(1064304859);
        com.verimatrix.schemas.omitypes.MessageOrigin _diagnoseOSM_messageMessageDescriptorVal1XOrigin = com.verimatrix.schemas.omitypes.MessageOrigin.ORIGR;
        _diagnoseOSM_messageMessageDescriptorVal1.setXOrigin(_diagnoseOSM_messageMessageDescriptorVal1XOrigin);
        _diagnoseOSM_messageMessageDescriptorVal1.setXPosition(1603025536);
        com.verimatrix.schemas.omitypes.MessageAnchor _diagnoseOSM_messageMessageDescriptorVal1XAnchor = com.verimatrix.schemas.omitypes.MessageAnchor.AUTOTD;
        _diagnoseOSM_messageMessageDescriptorVal1.setXAnchor(_diagnoseOSM_messageMessageDescriptorVal1XAnchor);
        com.verimatrix.schemas.omitypes.MessageOrigin _diagnoseOSM_messageMessageDescriptorVal1YOrigin = com.verimatrix.schemas.omitypes.MessageOrigin.ORIGTOP;
        _diagnoseOSM_messageMessageDescriptorVal1.setYOrigin(_diagnoseOSM_messageMessageDescriptorVal1YOrigin);
        _diagnoseOSM_messageMessageDescriptorVal1.setYPosition(6324709);
        com.verimatrix.schemas.omitypes.MessageAnchor _diagnoseOSM_messageMessageDescriptorVal1YAnchor = com.verimatrix.schemas.omitypes.MessageAnchor.AUTOTD;
        _diagnoseOSM_messageMessageDescriptorVal1.setYAnchor(_diagnoseOSM_messageMessageDescriptorVal1YAnchor);
        com.verimatrix.schemas.omitypes.MessageAlign _diagnoseOSM_messageMessageDescriptorVal1TextAlign = com.verimatrix.schemas.omitypes.MessageAlign.JUST;
        _diagnoseOSM_messageMessageDescriptorVal1.setTextAlign(_diagnoseOSM_messageMessageDescriptorVal1TextAlign);
        _diagnoseOSM_messageMessageDescriptorVal1.setBgColor(-1327913689);
        _diagnoseOSM_messageMessageDescriptorVal1.setAlpha(2052301262);
        _diagnoseOSM_messageMessageDescriptor.add(_diagnoseOSM_messageMessageDescriptorVal1);
        _diagnoseOSM_message.getMessageDescriptor().addAll(_diagnoseOSM_messageMessageDescriptor);
        com.verimatrix.schemas.omitypes.PackageList _diagnoseOSM_packageList = new com.verimatrix.schemas.omitypes.PackageList();
        java.util.List<com.verimatrix.schemas.omitypes.Package> _diagnoseOSM_packageListPackages = new java.util.ArrayList<com.verimatrix.schemas.omitypes.Package>();
        com.verimatrix.schemas.omitypes.Package _diagnoseOSM_packageListPackagesVal1 = new com.verimatrix.schemas.omitypes.Package();
        _diagnoseOSM_packageListPackagesVal1.setSmsPackageId("SmsPackageId-1993747314");
        _diagnoseOSM_packageListPackagesVal1.setDescription("Description-1906213776");
        com.verimatrix.schemas.omitypes.PackageAttributes _diagnoseOSM_packageListPackagesVal1PackageAttributes = new com.verimatrix.schemas.omitypes.PackageAttributes();
        _diagnoseOSM_packageListPackagesVal1PackageAttributes.setOperatorId("OperatorId-1436883527");
        _diagnoseOSM_packageListPackagesVal1PackageAttributes.setExtendedAccessClassId("ExtendedAccessClassId-898281657");
        _diagnoseOSM_packageListPackagesVal1.setPackageAttributes(_diagnoseOSM_packageListPackagesVal1PackageAttributes);
        com.verimatrix.schemas.omitypes.PackageParameters _diagnoseOSM_packageListPackagesVal1PackageParameters = new com.verimatrix.schemas.omitypes.PackageParameters();
        java.util.List<com.verimatrix.schemas.omitypes.Parameter> _diagnoseOSM_packageListPackagesVal1PackageParametersPackageParameter = new java.util.ArrayList<com.verimatrix.schemas.omitypes.Parameter>();
        _diagnoseOSM_packageListPackagesVal1PackageParameters.getPackageParameter().addAll(_diagnoseOSM_packageListPackagesVal1PackageParametersPackageParameter);
        _diagnoseOSM_packageListPackagesVal1.setPackageParameters(_diagnoseOSM_packageListPackagesVal1PackageParameters);
        _diagnoseOSM_packageListPackages.add(_diagnoseOSM_packageListPackagesVal1);
        _diagnoseOSM_packageList.getPackages().addAll(_diagnoseOSM_packageListPackages);
        com.verimatrix.schemas.omitypes.SessionHandle _diagnoseOSM_sessionHandle = new com.verimatrix.schemas.omitypes.SessionHandle();
        _diagnoseOSM_sessionHandle.setHandle("Handle1667111838");
        com.verimatrix.schemas.omitypes.Result _diagnoseOSM__return = port.diagnoseOSM(_diagnoseOSM_messageEntity, _diagnoseOSM_message, _diagnoseOSM_packageList, _diagnoseOSM_sessionHandle);
        System.out.println("diagnoseOSM.result=" + _diagnoseOSM__return);


        }
        {
        System.out.println("Invoking generatePackageOSM...");
        com.verimatrix.schemas.omitypes.MessageEntity _generatePackageOSM_messageEntity = new com.verimatrix.schemas.omitypes.MessageEntity();
        com.verimatrix.schemas.omitypes.MessageEntityType _generatePackageOSM_messageEntityEntityType = com.verimatrix.schemas.omitypes.MessageEntityType.DOMAIN;
        _generatePackageOSM_messageEntity.setEntityType(_generatePackageOSM_messageEntityEntityType);
        _generatePackageOSM_messageEntity.setEntityId("EntityId-928364055");
        _generatePackageOSM_messageEntity.setSmsNetworkId("SmsNetworkId1155344274");
        com.verimatrix.schemas.omitypes.OnScreenMessage _generatePackageOSM_message = new com.verimatrix.schemas.omitypes.OnScreenMessage();
        com.verimatrix.schemas.omitypes.MessageHandle _generatePackageOSM_messageMessageHandle = new com.verimatrix.schemas.omitypes.MessageHandle();
        _generatePackageOSM_messageMessageHandle.setSector(-437441503);
        _generatePackageOSM_messageMessageHandle.setMessageId(98408906);
        _generatePackageOSM_message.setMessageHandle(_generatePackageOSM_messageMessageHandle);
        com.verimatrix.schemas.omitypes.MessageAttributes _generatePackageOSM_messageMessageAttributes = new com.verimatrix.schemas.omitypes.MessageAttributes();
        com.verimatrix.schemas.omitypes.MessageContentMode _generatePackageOSM_messageMessageAttributesContentMode = com.verimatrix.schemas.omitypes.MessageContentMode.POS;
        _generatePackageOSM_messageMessageAttributes.setContentMode(_generatePackageOSM_messageMessageAttributesContentMode);
        _generatePackageOSM_message.setMessageAttributes(_generatePackageOSM_messageMessageAttributes);
        java.util.List<com.verimatrix.schemas.omitypes.MessageText> _generatePackageOSM_messageMessageText = new java.util.ArrayList<com.verimatrix.schemas.omitypes.MessageText>();
        com.verimatrix.schemas.omitypes.MessageText _generatePackageOSM_messageMessageTextVal1 = new com.verimatrix.schemas.omitypes.MessageText();
        _generatePackageOSM_messageMessageTextVal1.setText("Text-1237683732");
        _generatePackageOSM_messageMessageText.add(_generatePackageOSM_messageMessageTextVal1);
        _generatePackageOSM_message.getMessageText().addAll(_generatePackageOSM_messageMessageText);
        java.util.List<com.verimatrix.schemas.omitypes.MessageDescriptor> _generatePackageOSM_messageMessageDescriptor = new java.util.ArrayList<com.verimatrix.schemas.omitypes.MessageDescriptor>();
        com.verimatrix.schemas.omitypes.MessageDescriptor _generatePackageOSM_messageMessageDescriptorVal1 = new com.verimatrix.schemas.omitypes.MessageDescriptor();
        com.verimatrix.schemas.omitypes.MessageDisplayMode _generatePackageOSM_messageMessageDescriptorVal1DisplayMode = com.verimatrix.schemas.omitypes.MessageDisplayMode.RM_NEXTCA;
        _generatePackageOSM_messageMessageDescriptorVal1.setDisplayMode(_generatePackageOSM_messageMessageDescriptorVal1DisplayMode);
        _generatePackageOSM_messageMessageDescriptorVal1.setDisplayDuration(-1607644543);
        com.verimatrix.schemas.omitypes.MessageOrigin _generatePackageOSM_messageMessageDescriptorVal1XOrigin = com.verimatrix.schemas.omitypes.MessageOrigin.ORIGL;
        _generatePackageOSM_messageMessageDescriptorVal1.setXOrigin(_generatePackageOSM_messageMessageDescriptorVal1XOrigin);
        _generatePackageOSM_messageMessageDescriptorVal1.setXPosition(-1848844596);
        com.verimatrix.schemas.omitypes.MessageAnchor _generatePackageOSM_messageMessageDescriptorVal1XAnchor = com.verimatrix.schemas.omitypes.MessageAnchor.AUTOTD;
        _generatePackageOSM_messageMessageDescriptorVal1.setXAnchor(_generatePackageOSM_messageMessageDescriptorVal1XAnchor);
        com.verimatrix.schemas.omitypes.MessageOrigin _generatePackageOSM_messageMessageDescriptorVal1YOrigin = com.verimatrix.schemas.omitypes.MessageOrigin.ORIGDWN;
        _generatePackageOSM_messageMessageDescriptorVal1.setYOrigin(_generatePackageOSM_messageMessageDescriptorVal1YOrigin);
        _generatePackageOSM_messageMessageDescriptorVal1.setYPosition(-193423196);
        com.verimatrix.schemas.omitypes.MessageAnchor _generatePackageOSM_messageMessageDescriptorVal1YAnchor = com.verimatrix.schemas.omitypes.MessageAnchor.AUTOLR;
        _generatePackageOSM_messageMessageDescriptorVal1.setYAnchor(_generatePackageOSM_messageMessageDescriptorVal1YAnchor);
        com.verimatrix.schemas.omitypes.MessageAlign _generatePackageOSM_messageMessageDescriptorVal1TextAlign = com.verimatrix.schemas.omitypes.MessageAlign.JUST;
        _generatePackageOSM_messageMessageDescriptorVal1.setTextAlign(_generatePackageOSM_messageMessageDescriptorVal1TextAlign);
        _generatePackageOSM_messageMessageDescriptorVal1.setBgColor(-1364248302);
        _generatePackageOSM_messageMessageDescriptorVal1.setAlpha(-1892100887);
        _generatePackageOSM_messageMessageDescriptor.add(_generatePackageOSM_messageMessageDescriptorVal1);
        _generatePackageOSM_message.getMessageDescriptor().addAll(_generatePackageOSM_messageMessageDescriptor);
        com.verimatrix.schemas.omitypes.PackageList _generatePackageOSM_packageList = new com.verimatrix.schemas.omitypes.PackageList();
        java.util.List<com.verimatrix.schemas.omitypes.Package> _generatePackageOSM_packageListPackages = new java.util.ArrayList<com.verimatrix.schemas.omitypes.Package>();
        com.verimatrix.schemas.omitypes.Package _generatePackageOSM_packageListPackagesVal1 = new com.verimatrix.schemas.omitypes.Package();
        _generatePackageOSM_packageListPackagesVal1.setSmsPackageId("SmsPackageId452755848");
        _generatePackageOSM_packageListPackagesVal1.setDescription("Description-1696066453");
        com.verimatrix.schemas.omitypes.PackageAttributes _generatePackageOSM_packageListPackagesVal1PackageAttributes = new com.verimatrix.schemas.omitypes.PackageAttributes();
        _generatePackageOSM_packageListPackagesVal1PackageAttributes.setOperatorId("OperatorId1852272214");
        _generatePackageOSM_packageListPackagesVal1PackageAttributes.setExtendedAccessClassId("ExtendedAccessClassId-1199417367");
        _generatePackageOSM_packageListPackagesVal1.setPackageAttributes(_generatePackageOSM_packageListPackagesVal1PackageAttributes);
        com.verimatrix.schemas.omitypes.PackageParameters _generatePackageOSM_packageListPackagesVal1PackageParameters = new com.verimatrix.schemas.omitypes.PackageParameters();
        java.util.List<com.verimatrix.schemas.omitypes.Parameter> _generatePackageOSM_packageListPackagesVal1PackageParametersPackageParameter = new java.util.ArrayList<com.verimatrix.schemas.omitypes.Parameter>();
        _generatePackageOSM_packageListPackagesVal1PackageParameters.getPackageParameter().addAll(_generatePackageOSM_packageListPackagesVal1PackageParametersPackageParameter);
        _generatePackageOSM_packageListPackagesVal1.setPackageParameters(_generatePackageOSM_packageListPackagesVal1PackageParameters);
        _generatePackageOSM_packageListPackages.add(_generatePackageOSM_packageListPackagesVal1);
        _generatePackageOSM_packageList.getPackages().addAll(_generatePackageOSM_packageListPackages);
        com.verimatrix.schemas.omitypes.SessionHandle _generatePackageOSM_sessionHandle = new com.verimatrix.schemas.omitypes.SessionHandle();
        _generatePackageOSM_sessionHandle.setHandle("Handle-1379312795");
        com.verimatrix.schemas.omitypes.Result _generatePackageOSM__return = port.generatePackageOSM(_generatePackageOSM_messageEntity, _generatePackageOSM_message, _generatePackageOSM_packageList, _generatePackageOSM_sessionHandle);
        System.out.println("generatePackageOSM.result=" + _generatePackageOSM__return);


        }
        {
        System.out.println("Invoking removePreloadedMessage...");
        com.verimatrix.schemas.omitypes.MessageEntity _removePreloadedMessage_messageEntity = new com.verimatrix.schemas.omitypes.MessageEntity();
        com.verimatrix.schemas.omitypes.MessageEntityType _removePreloadedMessage_messageEntityEntityType = com.verimatrix.schemas.omitypes.MessageEntityType.DOMAIN;
        _removePreloadedMessage_messageEntity.setEntityType(_removePreloadedMessage_messageEntityEntityType);
        _removePreloadedMessage_messageEntity.setEntityId("EntityId1537440960");
        _removePreloadedMessage_messageEntity.setSmsNetworkId("SmsNetworkId-1696610432");
        com.verimatrix.schemas.omitypes.PreloadedMessage _removePreloadedMessage_message = new com.verimatrix.schemas.omitypes.PreloadedMessage();
        _removePreloadedMessage_message.setTextId(-595225580);
        _removePreloadedMessage_message.setSector(-194427120);
        com.verimatrix.schemas.omitypes.MessageAttributes _removePreloadedMessage_messageMessageAttributes = new com.verimatrix.schemas.omitypes.MessageAttributes();
        com.verimatrix.schemas.omitypes.MessageContentMode _removePreloadedMessage_messageMessageAttributesContentMode = com.verimatrix.schemas.omitypes.MessageContentMode.POS;
        _removePreloadedMessage_messageMessageAttributes.setContentMode(_removePreloadedMessage_messageMessageAttributesContentMode);
        _removePreloadedMessage_message.setMessageAttributes(_removePreloadedMessage_messageMessageAttributes);
        com.verimatrix.schemas.omitypes.MessageText _removePreloadedMessage_messageMessageText = new com.verimatrix.schemas.omitypes.MessageText();
        _removePreloadedMessage_messageMessageText.setText("Text-899208460");
        _removePreloadedMessage_message.setMessageText(_removePreloadedMessage_messageMessageText);
        com.verimatrix.schemas.omitypes.SessionHandle _removePreloadedMessage_sessionHandle = new com.verimatrix.schemas.omitypes.SessionHandle();
        _removePreloadedMessage_sessionHandle.setHandle("Handle2116076535");
        com.verimatrix.schemas.omitypes.Result _removePreloadedMessage__return = port.removePreloadedMessage(_removePreloadedMessage_messageEntity, _removePreloadedMessage_message, _removePreloadedMessage_sessionHandle);
        System.out.println("removePreloadedMessage.result=" + _removePreloadedMessage__return);


        }
        {
        System.out.println("Invoking generatePreloadedPackageOSM...");
        com.verimatrix.schemas.omitypes.MessageEntity _generatePreloadedPackageOSM_messageEntity = new com.verimatrix.schemas.omitypes.MessageEntity();
        com.verimatrix.schemas.omitypes.MessageEntityType _generatePreloadedPackageOSM_messageEntityEntityType = com.verimatrix.schemas.omitypes.MessageEntityType.DEVICE;
        _generatePreloadedPackageOSM_messageEntity.setEntityType(_generatePreloadedPackageOSM_messageEntityEntityType);
        _generatePreloadedPackageOSM_messageEntity.setEntityId("EntityId463176802");
        _generatePreloadedPackageOSM_messageEntity.setSmsNetworkId("SmsNetworkId23924806");
        com.verimatrix.schemas.omitypes.PreloadedOnScreenMessage _generatePreloadedPackageOSM_message = new com.verimatrix.schemas.omitypes.PreloadedOnScreenMessage();
        _generatePreloadedPackageOSM_message.setTextId(1932454276);
        _generatePreloadedPackageOSM_message.setDisplayDuration(-116162540);
        com.verimatrix.schemas.omitypes.MessageHandle _generatePreloadedPackageOSM_messageMessageHandle = new com.verimatrix.schemas.omitypes.MessageHandle();
        _generatePreloadedPackageOSM_messageMessageHandle.setSector(1183827126);
        _generatePreloadedPackageOSM_messageMessageHandle.setMessageId(-1716243766);
        _generatePreloadedPackageOSM_message.setMessageHandle(_generatePreloadedPackageOSM_messageMessageHandle);
        com.verimatrix.schemas.omitypes.MessageAttributes _generatePreloadedPackageOSM_messageMessageAttributes = new com.verimatrix.schemas.omitypes.MessageAttributes();
        com.verimatrix.schemas.omitypes.MessageContentMode _generatePreloadedPackageOSM_messageMessageAttributesContentMode = com.verimatrix.schemas.omitypes.MessageContentMode.NEG;
        _generatePreloadedPackageOSM_messageMessageAttributes.setContentMode(_generatePreloadedPackageOSM_messageMessageAttributesContentMode);
        _generatePreloadedPackageOSM_message.setMessageAttributes(_generatePreloadedPackageOSM_messageMessageAttributes);
        com.verimatrix.schemas.omitypes.MessageQuadrant _generatePreloadedPackageOSM_messageMessageQuadrant = com.verimatrix.schemas.omitypes.MessageQuadrant.NORTH_WEST;
        _generatePreloadedPackageOSM_message.setMessageQuadrant(_generatePreloadedPackageOSM_messageMessageQuadrant);
        com.verimatrix.schemas.omitypes.PackageList _generatePreloadedPackageOSM_packageList = new com.verimatrix.schemas.omitypes.PackageList();
        java.util.List<com.verimatrix.schemas.omitypes.Package> _generatePreloadedPackageOSM_packageListPackages = new java.util.ArrayList<com.verimatrix.schemas.omitypes.Package>();
        com.verimatrix.schemas.omitypes.Package _generatePreloadedPackageOSM_packageListPackagesVal1 = new com.verimatrix.schemas.omitypes.Package();
        _generatePreloadedPackageOSM_packageListPackagesVal1.setSmsPackageId("SmsPackageId1275082609");
        _generatePreloadedPackageOSM_packageListPackagesVal1.setDescription("Description-1260882183");
        com.verimatrix.schemas.omitypes.PackageAttributes _generatePreloadedPackageOSM_packageListPackagesVal1PackageAttributes = new com.verimatrix.schemas.omitypes.PackageAttributes();
        _generatePreloadedPackageOSM_packageListPackagesVal1PackageAttributes.setOperatorId("OperatorId-195676113");
        _generatePreloadedPackageOSM_packageListPackagesVal1PackageAttributes.setExtendedAccessClassId("ExtendedAccessClassId967911759");
        _generatePreloadedPackageOSM_packageListPackagesVal1.setPackageAttributes(_generatePreloadedPackageOSM_packageListPackagesVal1PackageAttributes);
        com.verimatrix.schemas.omitypes.PackageParameters _generatePreloadedPackageOSM_packageListPackagesVal1PackageParameters = new com.verimatrix.schemas.omitypes.PackageParameters();
        java.util.List<com.verimatrix.schemas.omitypes.Parameter> _generatePreloadedPackageOSM_packageListPackagesVal1PackageParametersPackageParameter = new java.util.ArrayList<com.verimatrix.schemas.omitypes.Parameter>();
        _generatePreloadedPackageOSM_packageListPackagesVal1PackageParameters.getPackageParameter().addAll(_generatePreloadedPackageOSM_packageListPackagesVal1PackageParametersPackageParameter);
        _generatePreloadedPackageOSM_packageListPackagesVal1.setPackageParameters(_generatePreloadedPackageOSM_packageListPackagesVal1PackageParameters);
        _generatePreloadedPackageOSM_packageListPackages.add(_generatePreloadedPackageOSM_packageListPackagesVal1);
        _generatePreloadedPackageOSM_packageList.getPackages().addAll(_generatePreloadedPackageOSM_packageListPackages);
        com.verimatrix.schemas.omitypes.SessionHandle _generatePreloadedPackageOSM_sessionHandle = new com.verimatrix.schemas.omitypes.SessionHandle();
        _generatePreloadedPackageOSM_sessionHandle.setHandle("Handle1263415376");
        com.verimatrix.schemas.omitypes.Result _generatePreloadedPackageOSM__return = port.generatePreloadedPackageOSM(_generatePreloadedPackageOSM_messageEntity, _generatePreloadedPackageOSM_message, _generatePreloadedPackageOSM_packageList, _generatePreloadedPackageOSM_sessionHandle);
        System.out.println("generatePreloadedPackageOSM.result=" + _generatePreloadedPackageOSM__return);


        }

        System.exit(0);
    }

}
