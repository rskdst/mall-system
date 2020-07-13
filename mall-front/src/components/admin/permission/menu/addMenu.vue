<template>
    <div class="add-menu">
      <a-icon type="close" style="font-size: 22px;float: right;margin-top: 1rem;margin-right: 1rem;cursor: pointer"
              @click="closeAdd"/>
      <a-form-model :rules="rules"
                    style="width:100%;margin-top:4rem;margin-left: 4rem;font-family: 宋体;font-weight: bold" :model="form"
                    :label-col="labelCol" :wrapper-col="wrapperCol" ref="ruleForm">
        <a-form-model-item label="菜单名称" prop="name">
          <a-input v-model="form.name"/>
        </a-form-model-item>
        <a-form-model-item label="上级菜单">
          <a-select v-model="form.parent" placeholder="无上级菜单">
            <a-select-option v-for="(item,index) in data" :value="item.bianhao" :key="index">
              {{item.name}}
            </a-select-option>
          </a-select>
        </a-form-model-item>
        <a-form-model-item label="菜单级别" prop="level">
          <a-input v-model="form.level"/>
        </a-form-model-item>
        <a-form-model-item label="路由地址">
          <a-input v-model="form.path"/>
        </a-form-model-item>
        <a-form-model-item label="前端图标">
          <a-input v-model="form.icon"/>
        </a-form-model-item>
        <a-form-model-item label="是否显示">
          <a-radio-group v-model="form.show" :default-value="1">
            <a-radio :value="1">是</a-radio>
            <a-radio :value="0">否</a-radio>
          </a-radio-group>
        </a-form-model-item>
        <a-form-model-item label="排序" prop="sort">
          <a-input v-model="form.sort"/>
        </a-form-model-item>
        <a-form-model-item :wrapper-col="{ span: 14, offset: 4 }">
          <a-button type="primary" @click="onSubmit">
            提交
          </a-button>
          <a-button style="margin-left: 10px;" @click="resetForm">
            重置
          </a-button>
        </a-form-model-item>
      </a-form-model>
    </div>
</template>

<script>
  //添加界面
  import {saveMenu,updateMenu} from '@/api/permission'
  export default {
    name: "addMenu",
    props: ["addflag", "data", "rowData"],
    data() {
      return {
        labelCol: {span: 4},
        wrapperCol: {span: 14},
        form: {
          name: '',
          parent: undefined,
          level:"",
          path: "",
          icon: "",
          show: undefined,
          sort: '',
        },
        rules: {
          name: [
            {required: true, message: '请输入菜单名称', trigger: 'blur'},
          ],
          level: [
            {required: true, message: '请输入菜单级别', trigger: 'blur'},
          ],
          sort: [
            {required: true, message: '请选择排序号', trigger: 'blur'},
          ]

        }
      }
    },
    methods: {
      initForm() { //初始化form
        this.form = {
          id:"",
          menuid:'',
          name: '',
          parent: undefined,
          level:"",
          path: "",
          icon: "",
          show: 1,
          sort: '',
        }
      },
      onSubmit() {
        this.$refs.ruleForm.validate(valid => {
          if (valid) {
            if (this.form.menuid==""){//保存操作
              saveMenu(this.form).then(res=>{
                console.log(res)
              })
            }else { //更新操作
              updateMenu(this.form).then(res=>{
                console.log(res)
              })
            }
          } else {
            console.log('请将必填项填写完整!');
            return false;
          }
        });
      },
      closeAdd() {
        this.$emit("addClose", false)
      },
      resetForm() {
        this.$refs.ruleForm.resetFields();
      },
      changeForm(formData) { //编辑行数据时渲染原数据
        this.initForm();
        this.form.id = formData.id;
        this.form.name = formData.name;
        if (formData.parent != "无上级菜单") {
          this.form.parent = formData.parent
        };
        this.form.level = formData.level;
        this.form.path = formData.address;
        this.form.icon = formData.icon;
        if (formData.show[0] == true) {
          this.form.show = 1
        } else {
          this.form.show = 0
        }
        this.form.sort = formData.sort;
        this.form.menuid = formData.bianhao;

      }
    },
  }
</script>

<style scoped>
  .add-menu {
    position: fixed;
    width: 30%;
    height: 40rem;
    background: #ffffff;
    margin: 0 25%;
    z-index: 2;
    border: 1px solid #E6E6E6;
    border-radius: 5px;
  }
</style>
