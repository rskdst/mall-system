<template>
  <a-config-provider :locale="locale">
    <a-table :columns="outColumns" :data-source="outData" bordered :pagination="pagination"
             :scroll="{ x: 1100, y: 400 }">
      <a-icon slot="icon" slot-scope="text" :type="text" style="font-size:20px;"/>
      <a-switch slot="show" slot-scope="text" :disabled="addFlag" :defaultChecked="text[0]" :v-model="text[0]"
                @click="changeSwitch(text)"/>
      <div slot="operation" slot-scope="text">
        <a href="#" @click="addFlag ? noEditor : editor(text[0])">{{text[0].title}}</a>
        &nbsp&nbsp&nbsp
        <a href="#" @click="addFlag ? noEditor : deleteMenu(text[1])">{{text[1].title}}</a>
      </div>
      <a-table
        slot="expandedRowRender"
        slot-scope="text"
        :columns="innerColumns"
        :data-source="text.expand"
        :pagination="false"
        :expand="text"
        bordered
      >
        <a-icon slot="innerIcon" slot-scope="text" :type="text" style="font-size:18px;"/>
        <a-switch slot="innerShow" disabled slot-scope="text" :defaultChecked="text" :v-model="text"/>
      </a-table>
    </a-table>
  </a-config-provider>
</template>

<script>
  import zhCN from "ant-design-vue/lib/locale/zh_CN";

  export default {
    name: "dataList",
    props: [
      "outColumns",
      "outData",
      "innerColumns",
      "innerData",
      "pagination",
      "addFlag",
    ],
    data(){
      return {
        locale: zhCN,
      }
    },
    methods:{
      changeSwitch(text){
        this.$parent.changeSwitch(text);
      },
      noEditor(){
        this.$parent.noEditor();
      },
      editor(text){
        this.$parent.editor(text);
      },
      deleteMenu(text){
        this.$parent.deleteMenu(text);
      }
    }
  }
</script>

<style scoped>

</style>
