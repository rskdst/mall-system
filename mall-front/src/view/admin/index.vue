<template>
  <div class="admin">
    <a-layout id="components-layout-demo-custom-trigger" style="min-height: 1080px">
      <a-layout-sider v-model="collapsed" :trigger="null" collapsible style="font-size: 30px">
        <a-menu theme="dark" mode="inline" :selectedKeys="[this.$route.path]">
          <a-menu-item key="/admin" @click="changeCurrentComponent('/admin')">
            <a-icon type="home"/>
            <span>首页</span>
          </a-menu-item>
          <template v-for="(item,index) in this.$store.state.app.routers">
            <a-sub-menu v-if="item.child.length>0" :key="index">
              <span slot="title"><a-icon :type="item.icon"/><span>{{item.menuname}}</span></span>
              <template v-for="(subItem,subIndex) in item.child">
                <template v-if="subItem.child.length>0">
                  <subMenu :key="subIndex" :subMenuData="subItem"></subMenu>
                </template>
                <template v-else>
                  <a-menu-item :key="subItem.path" @click="changeCurrentComponent(subItem)">
                    <a-icon :type="subItem.icon"/>
                    <span>{{subItem.menuname}}</span>
                  </a-menu-item>
                </template>
              </template>
            </a-sub-menu>
            <a-menu-item v-else :key="item.path" @click="changeCurrentComponent(item)">
              <a-icon :type="item.icon"/>
              <span>{{item.menuname}}</span>
            </a-menu-item>

          </template>
        </a-menu>
      </a-layout-sider>
      <a-layout>
        <a-layout-header style="background: #fff; padding: 0">
          <a-icon
            style="display: block"
            class="trigger"
            :type="collapsed ? 'menu-unfold' : 'menu-fold'"
            @click="() => (collapsed = !collapsed)"
          />
          <a-breadcrumb style="margin-left: 70px;margin-top: -3px;font-size: 16px;font-family: 黑体">
            <a-breadcrumb-item v-for="(item,index) in breadList" style="color: black;" v-if="index!=breadList.length-1"
                               :key="index"><a class="a-bread" :href="item.href">{{item.title}}</a></a-breadcrumb-item>
            <a-breadcrumb-item v-else style="color: grey;" :key="index">{{item.title}}</a-breadcrumb-item>

          </a-breadcrumb>
        </a-layout-header>
        <a-layout-content
          :style="{ margin: '24px 16px', padding: '24px', background: '#fff', minHeight: '280px' }"
        >
          <keep-alive>
            <router-view></router-view>
          </keep-alive>
        </a-layout-content>
      </a-layout>
    </a-layout>
  </div>
</template>

<script>
  import subMenu from "@/components/admin/permission/subMenu";

  export default {
    name: "index",
    components: {
      subMenu: subMenu,
    },
    data() {
      return {
        collapsed: false,
        menuList: '',
        breadList: [{title: "首页", href: "/admin"}],
      }
    },
    methods: {
      changeCurrentComponent(item) {
        this.breadList = [{title: "首页", href: "/admin"},]
        if (item === "/admin") {
          this.$router.push({
            path: item
          });
        } else {
          let pid = item.pid;
          let menus = this.$store.state.app.menuList;
          for (let i = 0; i < menus.length; i++) {
            if (menus[i].menuid === pid || menus[i].menuid === item.menuid) {
              this.breadList.push({
                title: menus[i].menuname,
                href: menus[i].path
              })
            }
          }
          this.$router.push({
            path: item.path
          })
        }
      },
    },
    mounted() {
      // getMenuList({id:this.$store.state.user.userId}).then(res => {
      //   if(res.code===200){
      //     this.menuList = res.data;
      //   }
      //
      // })
    }
  }
</script>

<style scoped>
  #components-layout-demo-custom-trigger .trigger {
    font-size: 22px;
    line-height: 64px;
    padding: 0 24px;
    cursor: pointer;
    transition: color 0.3s;
    color: #1890ff;
  }

  #components-layout-demo-custom-trigger .logo {
    height: 32px;
    background: rgba(255, 255, 255, 0.2);
    margin: 16px;
  }

  .ant-menu-item {
    margin: 0;
  }

  .anticon {
    width: 20px;
    height: 20px;
  }

  .a-bread {
    color: black;
  }

  .a-bread:hover {
    color: #1890ff;
  }
</style>
