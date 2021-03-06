/*
 * Copyright (c) 2015-2020, www.dibo.ltd (service@dibo.ltd).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package diboot.core.test.binder.vo;

import com.diboot.core.binding.annotation.BindEntity;
import com.diboot.core.binding.annotation.BindEntityList;
import com.diboot.core.binding.annotation.BindField;
import com.diboot.core.binding.annotation.BindFieldList;
import diboot.core.test.binder.entity.Organization;
import diboot.core.test.binder.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <Description>
 *
 * @author Jerry@dibo.ltd
 * @version v2.1.2
 * @date 2020/08/25
 */
@Getter @Setter
public class MulColMiddleJoinVO {

    private String status = "A";

    private Long departmentId;
    private String name;

    // 支持级联字段关联，相同条件的entity+condition将合并为一条SQL查询
    @BindField(entity = Organization.class, field="name", condition="this.department_id=department.id AND department.org_id=id")
    private String orgName;
    @BindField(entity = Organization.class, field="parentId", condition="this.department_id=department.id AND department.org_id=id")
    private Long orgParentId;

    private Long orgId;
    private String telphone;

    @BindEntity(entity = Organization.class, condition = "this.org_id=id AND this.telphone=telphone AND parent_id=0")
    private Organization organization;

    private String utype;
    private Long uid;

    @BindEntityList(entity = Role.class, condition = "this.utype=user_role.user_type AND this.uid=user_role.user_id AND user_role.role_id=id")
    private List<Role> roles;

    @BindFieldList(entity = Role.class, field = "code", condition = "this.utype=user_role.user_type AND this.uid=user_role.user_id AND user_role.role_id=id")
    private List<String> roleCodes;
    @BindFieldList(entity = Role.class, field = "name", condition = "this.utype=user_role.user_type AND this.uid=user_role.user_id AND user_role.role_id=id")
    private List<String> roleNames;

}
