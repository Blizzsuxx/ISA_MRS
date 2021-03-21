<template>
<h2>Lekovi koji su trenutno u ponudi</h2>
  <el-table
    ref="multipleTable"
    :data="tableData"
    style="width: 100%"
    @selection-change="handleSelectionChange">
    <el-table-column
      type="selection"
      width="55">
    </el-table-column>
    <el-table-column
      property="name"
      label="Name"
      width="120">
    </el-table-column>
    <el-table-column
      property="address"
      label="Address"
      show-overflow-tooltip>
    </el-table-column>
  </el-table>
  <div style="margin-top: 20px">
    <!-- <el-button @click="toggleSelection([tableData[1], tableData[2]])">Toggle selection status of second and third rows</el-button> -->
    <el-button @click="toggleSelection()">Očisti selekciju</el-button>
    <el-button @click="removeFromPharmacy()">Premesti u magacin</el-button>
  </div>
</template>

<script>
export default {
  name: 'APTrenutniLijekovi',
  data() {
      return {
        tableData: [{
          date: '2016-05-03',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles'
        }, {
          date: '2016-05-02',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles'
        }, {
          date: '2016-05-04',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles'
        }, {
          date: '2016-05-01',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles'
        }, {
          date: '2016-05-08',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles'
        }, {
          date: '2016-05-06',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles'
        }, {
          date: '2016-05-07',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles'
        }],
        multipleSelection: []
      }
    },
    mounted(){
      // console.log(this.$store.state.lijekovi.lijek)
      // console.log(this.$store.commit("lijekovi/postaviLijek","Paracetamol"))
      // console.log(this.$store.state.lijekovi.lijek)
    },
    methods: {
      toggleSelection(rows) {
        if (rows) {
          rows.forEach(row => {
            this.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.multipleTable.clearSelection();
        }
      },
      removeFromPharmacy(){
        //saljem vektor objekata u store za provjeru svakog lijeka i zatim
        console.log(this.multipleSelection)
        console.log(this.multipleSelection[0].date)
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      }
    }
  }

</script>
