package service;

import util.Util;

import javax.jws.WebService;

@WebService(endpointInterface = "service.RssService")
public class RssServiceImpl implements RssService {

    @Override
    public String getXML(String url) {
        System.out.println("Web service request: " + url);
        return Util.getRssBody(url);
    }
}