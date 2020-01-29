/*
 * Copyright © 2015 SYSTRAN Software, Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.systran.platform.translation.client.model;


import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.HashMap;
import java.util.Map;
/**
 * Batch Request
 **/
@ApiModel(description = "Batch Request")
public class BatchRequest  {
  
//  private String id = null;
  public enum StatusEnum {
    pending("pending"), finished("finished"), cancelled("cancelled"), error("error"); 

    private final String text;
    private static Map<String, StatusEnum> namesMap = new HashMap<String, StatusEnum>();
    
    static { 
      namesMap.put("pending", pending);
      namesMap.put("finished", finished);
      namesMap.put("cancelled", cancelled);
      namesMap.put("error", error);
    }
    
    private StatusEnum(final String text) {
      this.text = text;
    }

    @JsonCreator
    public static StatusEnum forValue(String value) throws Exception {
      if (namesMap.get(value) == null) {
        throw new Exception("Invalid enum value");
      }

      return namesMap.get(value);
    }

    @Override
    public String toString() {
      return text;
    }
  };
  private StatusEnum status = null;

  
//  /**
//   * Request identifier
//   **/
//  @ApiModelProperty(required = true, value = "Request identifier")
//  @JsonProperty("id")
//  public String getId() {
//    return id;
//  }
//  public void setId(String id) {
//    this.id = id;
//  }

  
  /**
   * Status of the request
   **/
  @ApiModelProperty(required = true, value = "Status of the request")
  @JsonProperty("status")
  public StatusEnum getStatus() {
    return status;
  }
  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class BatchRequest {\n");
    
//    sb.append("  id: ").append(id).append("\n");
    sb.append("  status: ").append(status).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
