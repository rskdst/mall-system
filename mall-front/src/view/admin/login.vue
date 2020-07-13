<template>
  <div class="main">
    <p>
      <label>用户名:</label>
      <a-input style="width:20rem"
        placeholder="请输入账号"
        v-model="$store.state.user.username"
        clearable>
      </a-input>
    </p>
    <p>
      <label>密&nbsp&nbsp&nbsp码:</label>
      <a-input-password style="width: 20rem" placeholder="请输入密码" v-model="password"></a-input-password>
    </p>
    <div>
      <a-button type="primary" round @click="login">登录</a-button>
      <a-button type="danger" round>忘记密码?</a-button>
    </div>


  </div>
</template>

<script>
  import {login} from "../../api/permission"

  export default {
    name: "login",
    data(){
      return{
        password:"",
      }
    },
    methods:{
      login(){
        login({username:this.$store.state.user.username,password:this.password}).then(res=>{
          if (res.code === 200){
            this.$router.push("/admin");
            console.log(res)
            this.$store.commit("setUserId",res.data.id);
            console.log(this)
          }
        })
      }
    },
    mounted() {
      // console.log(this)
    }
  }
</script>

<style scoped>
  .main {
    position: absolute;
    width: 25rem;
    height: 20rem;
    top: 25%;
    left: 40%;
    background: aquamarine;

  }

  p {
    text-align: center;
    margin-top: 2rem;
  }


  .main div {
    margin-top: 4rem;
    text-align: center;

  }
</style>
