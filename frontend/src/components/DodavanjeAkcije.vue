<template>
  <NavAdminApoteke />
    <el-alert
    v-if="prozor"
    @close="prozor=false"
    title="informacija"
    type="success" center>{{poruka}}
  </el-alert>
  <el-form
    label-position="top"
    label-width="100px"
    :model="forma"
  >
    <el-form-item label="Datum od">
      <el-date-picker v-model="forma.datumOd" type="date" placeholder="Izaberi dan"></el-date-picker>
    </el-form-item>
    <el-form-item label="Datum do">
      <el-date-picker v-model="forma.datumDo" type="date" placeholder="Izaberi dan"></el-date-picker>
    </el-form-item>
    <el-form-item label="Iznos">
       <el-input-number v-model="forma.popust" :min="1" :max="100"></el-input-number>
    </el-form-item>
    <el-form-item>
    <el-button type="primary" @click="kreirajAkciju">Kreiraj akciju</el-button>
    
        </el-form-item>
  </el-form>

  
</template>

<script>
import NavAdminApoteke from "./NavAdminApoteke";

export default {
  name: "DodavanjeAkcije",
  data() {
    return {
    prozor:false,
    poruka:"",
      forma:{
        datumDo: null,
        datumOd: null,
        popust: 1,
        id :this.$route.params.id,
      }
      
    };
  },
  methods: {
      kreirajAkciju(){
          this.$store.dispatch("APAkcije/kreirajAkciju",this.forma).then(()=>{
              this.poruka="Uspjesno kreirana akcija"
              this.prozor =true;
          })
      }
  },
  components: {
    NavAdminApoteke,
  },
  mounted() {},
};
</script>