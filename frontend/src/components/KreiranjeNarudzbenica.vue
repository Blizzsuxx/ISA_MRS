<template>
<NavAdminApoteke />
<el-alert
    v-if="greska"
    @close="greska=false"
    title="Greska pri prebacivanju"
    type="error" center>{{poruka}}
  </el-alert>
  <h3>Pregled svih lijekova</h3> 
  
  <LijekoviNarucivanjeTabela @promjenjena-selekcija="selektujRedove" ref='dijete' v-bind:lijekovi="$store.state.APlijekovi.dtoLijekovi" referenca="multipleTable" />
  <div style="margin-top: 20px">
    <el-button type="primary" @click="ocistiSelekciju()" plain>Očisti selekciju</el-button>
    
    <!-- Na ovo dugme se pali -->
    <el-button @click="otvoriProzor" plain type="primary">
        Dodaj novi lijek u narudžbenicu
    </el-button>

    <ModalniProzorNarudzbenica  ref="prozor"/>
  </div>
  <LijekoviNarucivanjeTabela kolicina="true" @promjenjena-selekcija="selektujRedove" ref='dijete2' v-bind:lijekovi="lijekoviZaPorucivanje" referenca="multipleTable1" />
  <el-date-picker
                v-model="datumRok"
                type="date"
                placeholder="Izaberite rok za davanje ponuda"
              ></el-date-picker>
  <el-button type="primary" @click="zavrsiNarucivanje">
    Zavrsi narucivanje</el-button>



<el-dialog
  title="Obavjestenje o kreiranju"
  v-model="dialogVisible"
  width="30%"
  :before-close="handleClose">
  <span>Narudzbina uspjesno kreirana</span>
  <template #footer>
    <span class="dialog-footer">
     
    </span>
  </template>
</el-dialog>
</template>  
 

<script>
//import {mapState} from 'vuex'
import NavAdminApoteke from "./NavAdminApoteke";
import LijekoviNarucivanjeTabela from './LijekoviNarucivanjeTabela'
import ModalniProzorNarudzbenica from './modal/ModalniProzorNarudzbenica'
export default {
  name: 'KreiranjeNarudzbenica',
  data() {
      return {
        greska : false,
        poruka : "",
        prozor: false,
        modalOpen: false,
        datumRok: new Date(),
        dialogVisible : false,
      }
    },
    components:{
      
      ModalniProzorNarudzbenica,
      LijekoviNarucivanjeTabela,
      NavAdminApoteke
    },
    mounted(){
      
      this.$store.dispatch("APlijekovi/dobaviDTOLijekove").then(response=>{
          
          return response;
      }

      )
     
      
    },
    computed: {
      lijekoviZaPorucivanje(){
        return this.$store.state.APlijekovi.lijekoviZaPorucivanje;
      }
  
  },
    methods: {
      ocistiSelekciju(rows) {
        if (rows) {
          rows.forEach(row => {
            this.$refs.dijete.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.dijete.$refs.multipleTable.clearSelection();
        }
      },
      handleClose(){
        this.dialogVisible=false;
      },
      otvoriProzor(){

        if(this.$refs.dijete.multipleSelection.length!=1 ){
          this.greska=true;
          this.poruka = `Samo jedan lijek treba biti selektovan pri mijenjanju cijene`;
        }
        else{

        
        this.$refs.prozor.lijek.id = this.$refs.dijete.multipleSelection[0].id
        this.$refs.prozor.modalOpen = true;

        }
       
        

      },
      selektujRedove(val) {
        this.$refs.dijete.multipleSelection = val;
      },
      zavrsiNarucivanje(){
        if(this.$store.state.APlijekovi.lijekoviZaPorucivanje.length===0){
          this.greska=true;
          this.poruka = `Mora biti bar jedan lijek selektovan za naručivanje`;
          return;
        }
        this.$store.dispatch("APlijekovi/zavrsiNarucivanje",this.datumRok)
        this.dialogVisible=true
       
      }
    }
  }

</script>