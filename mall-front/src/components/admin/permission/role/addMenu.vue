<template>
  <div class="add-menu">
    <a-icon type="close" style="font-size: 22px;float: right;margin-top: 1rem;margin-right: 1rem;cursor: pointer"
            @click="closeAdd"/>
    <a-form-model :rules="rules"
                  style="width:100%;margin-top:4rem;margin-left: 4rem;font-family: 宋体;font-weight: bold" :model="form"
                  :label-col="labelCol" :wrapper-col="wrapperCol" ref="ruleForm">
      <a-form-model-item label="角色名称" prop="name">
        <a-input v-model="form.name"/>
      </a-form-model-item>
      <a-form-model-item label="角色描述">
        <a-textarea :auto-size="{ minRows: 5, maxRows: 7 }" v-model="form.description"/>
      </a-form-model-item>
      <a-form-model-item label="是否启用">
        <a-radio-group v-model="form.show" :default-value="1">
          <a-radio :value="1">是</a-radio>
          <a-radio :value="0">否</a-radio>
        </a-radio-group>
      </a-form-model-item>
      <a-form-model-item :wrapper-col="{ span: 14, offset: 4 }">
        <a-button type="primary" @click="onSubmit">
          提交
        </a-button>
        <a-button style="margin-left: 100px;" @click="resetForm">
          重置
        </a-button>
      </a-form-model-item>
    </a-form-model>
  </div>
</template>

<script>
  export default {
    name: "addMenu",
    props: ["addflag", "data", "rowData"],
    data() {
      return {
        labelCol: {span: 4},
        wrapperCol: {span: 14},
        rules: {
          name: [
            {required: true, message: '请输入角色名称', trigger: 'blur'},
          ],
        },
        form: {
          name: "",
          description: "",
          show: 1,
        }
      }
    },
    methods: {
      closeAdd() {
        this.$emit("addClose", false)
      },
      onSubmit() {
        this.$refs.ruleForm.validate(valid => {
          if (valid) {
            console.log(this.form)
          } else {
            console.log('请将必填项填写完整!');
            return false;
          }
        });
      },
      resetForm() {
        this.$refs.ruleForm.resetFields();
      }
    }
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
