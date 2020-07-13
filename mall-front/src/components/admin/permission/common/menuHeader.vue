<template>
  <div class="menu-header">
    <div class="header-name">
      <a-icon type="book"/>
      <span>数据列表</span>
    </div>
    <a-button type="default" class="menu-add" :disabled="addFlag" @click="menuAdd">添加</a-button>
  </div>
</template>

<script>
  export default {
    props:[
      "addFlag"
    ],
    name: "menuHeader",
    data(){
      return{

      }
    },
    methods:{
      menuAdd(){
        this.$parent.menuAdd();
      }
    }
  }
</script>

<style scoped>
  .menu-header {
    width: 100%;
    height: 4rem;
    margin: 1rem 0;
    border: 1px solid #E6E6E6;
    border-radius: 5px;
  }

  .header-name {
    float: left;
    font-size: 16px;
    margin-left: 1rem;
    width: 10rem;
    height: 4rem;
    line-height: 4rem;
  }

  .menu-add {
    float: right;
    margin-right: 1rem;
    margin-top: 1rem;
  }
</style>
