<template functional>
    <a-sub-menu :key="props.subMenuData.path">
            <span slot="title"><a-icon :type="props.subMenuData.icon" /><span>{{props.subMenuData.menuname}}</span></span>
      <template v-for="(subMenuItem,subMenuIndex) in props.subMenuData.child">
                <template v-if="subMenuItem.child.length>0">
                  <sub-menu :key="subMenuIndex" :subMenuArr="subMenuItem"></sub-menu>
                </template>
        <template>
          <a-menu-item :key="subMenuItem.path">
            <a-icon :type="subMenuItem.icon"/>
            <span>{{subMenuItem.menuname}}</span>
          </a-menu-item>
        </template>
      </template>
    </a-sub-menu>
</template>

<script>
  export default {
    name: "subMenu",
    props: {
      subMenuData:{
        type:Object,
        default:()=>({})
      }
    },
  }
</script>

<style scoped>


</style>
