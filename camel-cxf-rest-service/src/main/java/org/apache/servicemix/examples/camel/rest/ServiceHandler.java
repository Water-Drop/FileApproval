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

package org.apache.servicemix.examples.camel.rest;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.servicemix.examples.camel.rest.model.File;

public class ServiceHandler {


    public void init(){
    }


    public Response getApprovalStatus(File file){
       String regEx="^[A-Za-z0-9.]+$";
       Pattern p=Pattern.compile(regEx);
       Matcher m=p.matcher(file.getFilename());
       Boolean result=m.find();
       if (result == true) {
           if (file.getSize()>2000){
               result = false;
           }
       }
       ResponseBuilder builder = Response.status(Status.OK);   
       builder.entity("FileId:" + file.getId() + " FileName:" + file.getFilename() + " FileStatus:" + result.toString());
       return builder.build();
    }

}
