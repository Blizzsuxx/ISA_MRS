<template>
  <APDermatolog ref="table">
    <el-menu-item index="1-3">Izdavanje lekova</el-menu-item>

    <template v-slot:header-slot>
      <el-select v-model="value" filterable placeholder="Select">
        <el-option
          v-for="item in tableData"
          :key="item.apoteka.ime"
          :label="item.apoteka.ime"
          :value="item.apoteka.ime">
        </el-option>
      </el-select>
    </template>
  </APDermatolog>
</template>

<script>
  import APDermatolog from './APDermatolog.vue'
  export default {
  components: { APDermatolog },
    name: 'APFarmaceut',
    methods: {
      promena(value){
          //APDermatolog.storageData.filter( (item) => {return item.pharmacy === value} );
          if(this.$refs.table.tableData.length == 0){
            this.$refs.table.tableData = this.$store.state.APPosete.svePosete;
          }
          this.$refs.table.tableData = this.$refs.table.tableData.filter( (item) => {return item.pharmacy === value} );
      }
    },
    data() {

      return {
        tableData: null,
        
      }
    }
  };
</script>