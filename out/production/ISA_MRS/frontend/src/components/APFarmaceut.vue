<template>
  <APDermatolog ref="table">
    <el-menu-item index="1-3">Izdavanje lekova</el-menu-item>

    <template v-slot:header-slot>
      <Selecter options="No. 189, Grove St, Los Angeles;apoteka2" @change="promena" ></Selecter>
    </template>
  </APDermatolog>
</template>

<script>
  import APDermatolog from './APDermatolog.vue'
  import Selecter from './Selecter'
  export default {
  components: { APDermatolog, Selecter },
    name: 'APFarmaceut',
    methods: {
      promena(value){
          //APDermatolog.storageData.filter( (item) => {return item.pharmacy === value} );
          this.$refs.table.tableData = this.$refs.table.storageData.filter( (item) => {return item.pharmacy === value} );
      }
    },
    data() {

      return {
        tableData: null,
        storageData : null,
      }
    }
  };
</script>