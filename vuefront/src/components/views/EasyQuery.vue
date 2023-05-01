<template>
  <el-table :data="tableData" highlight-current-row border stripe style="width: 100%" class="userTable">
    <el-table-column v-for="value in headers" :prop="value" :key="value" :label="value" :width="200" show-overflow-tooltip>
    </el-table-column>
  </el-table>


</template>

<script>
import axios from "axios";
export default {
  name: "EasyQuery",
  data() {
    return {
      headers: [],
      tableData: [],
      reqPara: {
        "sql": "select * from t_movie",
        "pageNum": 1,
        "pageSize": 10,
        "total": "",
        "dataSource": {
          "url": "jdbc:mysql://localhost/dsbt?useSSL=false&useUnicode=true&characterEncoding=UTF8&serverTimezone=GMT",
          "username": "Appleye",
          "password": "Yosug4n@"
        }
      },
    }
  },
  methods: {
    queryUserList() {
      axios.post("http://localhost:8080/getExecuteResult", this.reqPara).then(
          //成功回调
          (success) => {
            console.log("请求成功");
            //请求成功后的数据
            this.headers = Object.keys(success.data.data[0]);
            this.tableData = success.data.data;
          },
          //回调失败
          (error) => {
            console.log("请求失败");
            //请求失败原因
            console.log(error.message);
          }
      );
    },
  },
  mounted() {
    this.queryUserList();
  },
}
</script>

<style scoped>

</style>