<template>
  <APDermatolog ref="table">
    <el-link href="/ap/farmaceut/izdavanje">
    <el-menu-item index="1-1" >Izdavanje lekova</el-menu-item>
    </el-link>

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
      console.log("C");
      return {
        tableData: this.$store.state.APPosete.svePosete,
        radnik: this.$store.state.APKorisnici.trenutniRadnik,
        redirectString: "farmaceut",
        value : null
      }
    }
    ,

    async mounted(){
      console.log("A");
      await this.$store.dispatch("APKorisnici/promeniRedirekciju", "farmaceut");
      console.log(this.$store.state.APKorisnici.trenutnaRedirekcija);
    },
  };
</script>