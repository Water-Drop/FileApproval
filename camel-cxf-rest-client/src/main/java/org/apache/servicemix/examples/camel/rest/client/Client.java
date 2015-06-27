/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.servicemix.examples.camel.rest.client;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.cxf.helpers.IOUtils;
import org.apache.servicemix.examples.camel.rest.model.File;

public class Client {
    private static final String FILE_SERVICE_URL = "http://localhost:8989/rest/fileservice/";

    public static void main(String[] args) {
        Client client = new Client();
        try {
            client.postFile(new File(1, "JohnSmith.txt", 1024));
            client.postFile(new File(2, "JohnSmith.txt", 2048));
            client.postFile(new File(3, "JohnSm@ith.txt", 100));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void postFile(File file) throws Exception{
        System.out.println("\n### POST File -> ");
        HttpURLConnection connection = connect(FILE_SERVICE_URL + "file/getapproval/");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/xml");

        JAXBContext jaxbContext = JAXBContext.newInstance(File.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // pretty xml output
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(file, System.out);
        jaxbMarshaller.marshal(file, connection.getOutputStream());

        System.out.println("\n### POST FILE RESPONSE");
        InputStream stream = connection.getResponseCode() / 100 == 2 ?
                connection.getInputStream() : connection.getErrorStream();
        System.out.println("Status: " + connection.getResponseCode() +  " " + 
                connection.getResponseMessage());
        System.out.println(IOUtils.toString(stream));
    }


    private HttpURLConnection connect(String url) throws Exception{
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        return connection;
    }

}
