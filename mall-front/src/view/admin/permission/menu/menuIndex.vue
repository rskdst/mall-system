<template>
  <div>
    <add-menu ref="addMenu" v-show="addFlag" :data="data" :rowData="currentRowData" :addflag="addFlag"
              @addClose="addclose"></add-menu>
    <search :searchForm="searchForm"></search>
    <menu-header :addFlag="addFlag"></menu-header>
    <div class="menu-content">
      <data-list :outColumns="columns" :outData="data" :innerColumns="innerColumns" :innerData="innerData"
      :pagination="pagination" :addFlag="addFlag"></data-list>
    </div>
  </div>
</template>

<script>
  import {getMenuList, getMenuListByCondition} from '@/api/permission'
  import addMenu from "@/components/admin/permission/menu/addMenu";
  import dataList from "@/components/admin/permission/common/dataList";
  import search from "@/components/admin/permission/common/search"
  import menuHeader from "@/components/admin/permission/common/menuHeader";

  export default {
    name: "menuIndex",
    components: {
      addMenu,
      dataList,
      search,
      menuHeader
    },
    data() {
      return {
        addFlag: false,
        currentRowData: [],
        searchForm: [
          {
            title:"菜单名称",
            value:"",
            type:"input",
          },{
            title:"菜单级别",
            value:"",
            type:"input",
          },{
            title:"显示",
            value:"",
            type:"select",
          },{
            title:"上级菜单",
            value:"",
            type:"input",
          }
        ],
        pagination: {
          pageSize: 5, // 一页的数据限制
          current: 1, // 当前页
          total: 0, // 总数
          hideOnSinglePage: false, // 只有一页时是否隐藏分页器
          showQuickJumper: true, // 是否可以快速跳转至某页
          showSizeChanger: true, // 是否可以改变 pageSize
          pageSizeOptions: ['5', '10', '20'], // 指定每页可以显示多少条
          showTotal: total => `共 ${total}条`, // 可以展示总数
          onShowSizeChange: (current, pageSize) => { // 改变 pageSize时的回调
            this.pagination.current = current
            this.pagination.pageSize = pageSize
            //   ....
          },
          onChange: (current) => { // 切换分页时的回调，
            // 当在页面定义change事件时，切记要把此处的事件清除，因为这两个事件重叠了，可能到时候会导致一些莫名的bug
            this.pagination.current = current
          },
        },
        columns: [
          {
            title: '编号',
            dataIndex: 'bianhao',
            key: 'bianhao',
            align: "center",
            width: 100,
          }, {
            title: '菜单名称',
            dataIndex: 'name',
            align: "center",
            key: 'name',
            width: 200,
          }, {
            title: '菜单级别',
            dataIndex: 'level',
            align: "center",
            key: 'level',
            width: 180,
          }, {
            title: '路由地址',
            dataIndex: 'address',
            align: "center",
            key: 'address',
            width: 300,
          }, {
            title: '前端图标',
            dataIndex: 'icon',
            key: 'icon',
            align: "center",
            width: 100,
            scopedSlots: {customRender: 'icon'},
          }, {
            title: '是否显示',
            dataIndex: 'show',
            key: 'show',
            align: "center",
            width: 100,
            scopedSlots: {customRender: 'show'},
          }, {
            title: '排序',
            dataIndex: 'sort',
            key: 'sort',
            align: "center",
            width: 100,
          }, {
            title: '父级菜单',
            dataIndex: 'parent',
            key: 'parent',
            align: "center",
            width: 150,
          }, {
            title: '操作',
            dataIndex: 'operation',
            key: 'operation',
            align: "center",
            width: 200,
            scopedSlots: {customRender: 'operation'}
          }
        ],
        data: [],
        innerColumns: [
          {
            title: '编号',
            dataIndex: 'innerBianhao',
            key: 'innerBianhao',
            align: "center",
            width: 100,
          }, {
            title: '菜单名称',
            dataIndex: 'innerName',
            align: "center",
            key: 'innerName',
            width: 300,
          }, {
            title: '菜单级别',
            dataIndex: 'innerLevel',
            align: "center",
            key: 'innerLevel',
            width: 180,
          }, {
            title: '路由地址',
            dataIndex: 'innerAddress',
            align: "center",
            key: 'innerAddress',
            width: 400,
          }, {
            title: '前端图标',
            dataIndex: 'innerIcon',
            key: 'innerIcon',
            align: "center",
            width: 100,
            scopedSlots: {customRender: 'innerIcon'},
          }, {
            title: '是否显示',
            dataIndex: 'innerShow',
            key: 'innerShow',
            align: "center",
            width: 100,
            scopedSlots: {customRender: 'innerShow'},
          }, {
            title: '排序',
            dataIndex: 'innerSort',
            key: 'innerSort',
            align: "center",
            width: 100,
          },
        ],
        innerData: [],
      }
    },
    methods: {
      getData(menuData) { //将取到的数据放入data中渲染页面
        this.pagination.total = menuData.length;
        for (let i in menuData) {
          //获取父级菜单名称
          let pid = menuData[i].pid;
          let parentName;
          if (pid == "0") {
            parentName = "无上级菜单";
          } else {
            let menuList = this.$store.state.app.allMenuList;
            for (let j in menuList) {
              if (menuList[j].menuid == pid) {
                parentName = menuList[j].menuName;
              }
            }
          }
          //获取所有子菜单
          let menuid = menuData[i].menuid;
          let sonMenuList = [];
          for (let k in menuData) {
            if (menuData[k].pid == menuid) {
              let innerJsonData = {
                "innerBianhao": menuData[k].menuid,
                "key": k.toString(),
                "innerName": menuData[k].menuName,
                "innerLevel": menuData[k].level,
                "innerAddress": menuData[k].path,
                "innerIcon": menuData[k].icon,
                "innerShow": menuData[k].showmenu == "0" ? false : true,
                "innerSort": menuData[k].sort,
              }
              sonMenuList.push(innerJsonData)
            }
          }
          // console.log(menuData[i].showmenu)
          let jsonData = {
            "id":menuData[i].id,
            "bianhao": menuData[i].menuid,
            "key": i.toString(),
            "name": menuData[i].menuName,
            "level": menuData[i].level,
            "address": menuData[i].path,
            "icon": menuData[i].icon,
            "sort": menuData[i].sort,
            "parent": parentName,
            "show": [menuData[i].showmenu == "0" ? false : true, i],
            "operation": [{title: "编辑", index: i}, {title: "删除", index: i}],
            "expand": sonMenuList,
          };
          this.data.push(jsonData)

        }
      },
      onChange(pageNumber) {
        // console.log('Page: ', pageNumber);
      },
      changeSwitch(text) {
        this.data[text[1]].show[0] = !text[0]
      },
      menuAdd() { //添加菜单
        this.addFlag = !this.addFlag;
        this.$refs.addMenu.initForm();

      },
      addclose(addflag) { //关闭添加菜单界面
        this.addFlag = addflag
      },
      editor(item) { //编辑按钮
        let index = item.index;
        this.$refs.addMenu.changeForm(this.data[index]);
        this.addFlag = true
      },
      noEditor() {
      },
      deleteMenu(item) { //删除菜单操作
        console.log(item)
      },
      search() { //根据条件删选菜单
        let form = {
          "name":this.searchForm[0].value,
          "level":this.searchForm[1].value,
          "show":this.searchForm[2].value,
          "parent":this.searchForm[3].value
        };
        this.data = [];
        getMenuListByCondition(form).then(res => {
          let menuData = res.data;
          this.getData(menuData);
        })
      },
      initSearchForm(){//初始化搜索框
        this.searchForm = [
          {
            title:"菜单名称",
            value:"",
            type:"input",
          },{
            title:"菜单级别",
            value:"",
            type:"input",
          },{
            title:"显示",
            value:"",
            type:"select",
          },{
            title:"上级菜单",
            value:"",
            type:"input",
          }
        ]
      }

    },
    mounted() {
      getMenuList({}).then(res => {
        this.data = [];
        let menuData = res.data;
        this.$store.state.app.allMenuList = menuData;
        this.getData(menuData);

      })
    },
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

  .menu-foot {
    float: right;
    margin-top: 1rem;
  }

</style>
