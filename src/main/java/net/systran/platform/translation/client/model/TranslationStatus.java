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

import net.systran.platform.translation.client.model.ErrorResponse;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.HashMap;
import java.util.Map;

@ApiModel(description = "")
public class TranslationStatus  {
  
  private ErrorResponse error = null;
//  private String batchId = null;
  private Boolean cancelled = null;
  private Double createdAt = null;
  private String description = null;
  private Double expireAt = null;
  private Double finishedAt = null;
  private Integer finishedSteps = null;
  public enum StatusEnum {
    registered("registered"), _import("import"), started("started"), pending("pending"), export("export"), finished("finished"), error("error"); 

    private final String text;
    private static Map<String, StatusEnum> namesMap = new HashMap<String, StatusEnum>();
    
    static { 
      namesMap.put("registered", registered);
      namesMap.put("import", _import);
      namesMap.put("started", started);
      namesMap.put("pending", pending);
      namesMap.put("export", export);
      namesMap.put("finished", finished);
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
  private Integer totalSteps = null;

  
  /**
   * Error of the request
   **/
  @ApiModelProperty(value = "Error of the request")
  @JsonProperty("error")
  public ErrorResponse getError() {
    return error;
  }
  public void setError(ErrorResponse error) {
    this.error = error;
  }

  
//  /**
//   * Batch Identifier
//   **/
//  @ApiModelProperty(value = "Batch Identifier")
//  @JsonProperty("batchId")
//  public String getBatchId() {
//    return batchId;
//  }
//  public void setBatchId(String batchId) {
//    this.batchId = batchId;
//  }

  
  /**
   * Is the request cancelled
   **/
  @ApiModelProperty(value = "Is the request cancelled")
  @JsonProperty("cancelled")
  public Boolean getCancelled() {
    return cancelled;
  }
  public void setCancelled(Boolean cancelled) {
    this.cancelled = cancelled;
  }

  
  /**
   * Creation time of the request (ms since the Epoch)
   **/
  @ApiModelProperty(required = true, value = "Creation time of the request (ms since the Epoch)")
  @JsonProperty("createdAt")
  public Double getCreatedAt() {
    return createdAt;
  }
  public void setCreatedAt(Double createdAt) {
    this.createdAt = createdAt;
  }

  
  /**
   * Description
   **/
  @ApiModelProperty(required = true, value = "Description")
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  
  /**
   * Expiration time of the request (ms since the Epoch)
   **/
  @ApiModelProperty(required = true, value = "Expiration time of the request (ms since the Epoch)")
  @JsonProperty("expireAt")
  public Double getExpireAt() {
    return expireAt;
  }
  public void setExpireAt(Double expireAt) {
    this.expireAt = expireAt;
  }

  
  /**
   * Completion time of the request (ms since the Epoch)
   **/
  @ApiModelProperty(value = "Completion time of the request (ms since the Epoch)")
  @JsonProperty("finishedAt")
  public Double getFinishedAt() {
    return finishedAt;
  }
  public void setFinishedAt(Double finishedAt) {
    this.finishedAt = finishedAt;
  }

  
  /**
   * Number of finished steps
   **/
  @ApiModelProperty(required = true, value = "Number of finished steps")
  @JsonProperty("finishedSteps")
  public Integer getFinishedSteps() {
    return finishedSteps;
  }
  public void setFinishedSteps(Integer finishedSteps) {
    this.finishedSteps = finishedSteps;
  }

  
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

  
  /**
   * Number of steps to complete
   **/
  @ApiModelProperty(required = true, value = "Number of steps to complete")
  @JsonProperty("totalSteps")
  public Integer getTotalSteps() {
    return totalSteps;
  }
  public void setTotalSteps(Integer totalSteps) {
    this.totalSteps = totalSteps;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class TranslationStatus {\n");
    
    sb.append("  error: ").append(error).append("\n");
//    sb.append("  batchId: ").append(batchId).append("\n");
    sb.append("  cancelled: ").append(cancelled).append("\n");
    sb.append("  createdAt: ").append(createdAt).append("\n");
    sb.append("  description: ").append(description).append("\n");
    sb.append("  expireAt: ").append(expireAt).append("\n");
    sb.append("  finishedAt: ").append(finishedAt).append("\n");
    sb.append("  finishedSteps: ").append(finishedSteps).append("\n");
    sb.append("  status: ").append(status).append("\n");
    sb.append("  totalSteps: ").append(totalSteps).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
