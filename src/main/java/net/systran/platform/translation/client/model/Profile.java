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

/**
 * Profile
 **/
@ApiModel(description = "Profile")
public class Profile  {
  
  private String id = null;
  private Object localization = null;
  private String name = null;
  private String source = null;
  private String target = null;

  
  /**
   * Profile identifier
   **/
  @ApiModelProperty(required = true, value = "Profile identifier")
  @JsonProperty("id")
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  
  /**
   * Localization of the profile name
   **/
  @ApiModelProperty(value = "Localization of the profile name")
  @JsonProperty("localization")
  public Object getLocalization() {
    return localization;
  }
  public void setLocalization(Object localization) {
    this.localization = localization;
  }

  
  /**
   * Name
   **/
  @ApiModelProperty(value = "Name")
  @JsonProperty("name")
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  
  /**
   * Source
   **/
  @ApiModelProperty(required = true, value = "Source")
  @JsonProperty("source")
  public String getSource() {
    return source;
  }
  public void setSource(String source) {
    this.source = source;
  }

  
  /**
   * Target
   **/
  @ApiModelProperty(required = true, value = "Target")
  @JsonProperty("target")
  public String getTarget() {
    return target;
  }
  public void setTarget(String target) {
    this.target = target;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Profile {\n");
    
    sb.append("  id: ").append(id).append("\n");
    sb.append("  localization: ").append(localization).append("\n");
    sb.append("  name: ").append(name).append("\n");
    sb.append("  source: ").append(source).append("\n");
    sb.append("  target: ").append(target).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
