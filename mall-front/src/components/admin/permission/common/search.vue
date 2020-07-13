<template>
  <div class="menu-search">
    <p style="margin: 10px 10px; font-weight: bold;font-size: 18px">
      <a-icon type="search"/>
      筛选搜索
    </p>
    <div class="search-term" style="float: left;margin-left: 2rem;font-size:18px">
      <template v-for="(form,index) in searchForm">
        <div v-if="form.type=='input'">
          <lable v-text="form.title+'：'"></lable>
          <a-input v-model="form.value" :value="form.value" :placeholder="form.title" style="width: 13rem"/>
          <span>&nbsp&nbsp&nbsp</span>
        </div>
        <div v-else-if="form.type=='select'">
          <lable v-text="form.title+'：'"></lable>
          <a-select style="width: 5rem" v-model="form.value">
            <a-select-option value="1">
              是
            </a-select-option>
            <a-select-option value="0">
              否
            </a-select-option>
          </a-select>
          <span>&nbsp&nbsp&nbsp</span>
        </div>
      </template>
    </div>
    <div class="search-btn" style="float: right;margin-right: 2rem">
      <a-config-provider :auto-insert-space-in-button="false">
        <a-button type="primary" @click="search">
          搜索
        </a-button>
      </a-config-provider>
      <span>&nbsp&nbsp&nbsp</span>
      <a-button type="defalut" @click="initSearchForm">重置
      </a-button>
    </div>
  </div>
</template>

<script>
  //筛选搜索框
  export default {
    props:[
      "searchForm",
    ],
    name: "search",
    methods:{
      initSearchForm(){
        this.$parent.initSearchForm();
      },
      search(){
        this.$parent.search();
      }
    }
  }
</script>

<style scoped>
  .menu-search {
    width: 100%;
    height: 6rem;
    margin: 1rem 0;
    border: 1px solid #E6E6E6;
    border-radius: 5px;
  }
  .search-term div{
    display: inline-block;
  }
</style>
