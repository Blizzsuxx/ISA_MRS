<template>
<el-alert
    v-if="greska"
    @close="greska=false"
    title="Greska pri prebacivanju"
    type="error" center>{{poruka}}
  </el-alert>
  <h3>Pregled svih lijekova</h3> 
  
  <LijekoviTabela @promjenjena-selekcija="selektujRedove" ref='dijete' v-bind:lijekovi="$store.state.APlijekovi.sviLijekovi" referenca="multipleTable" />
  <div style="margin-top: 20px">
    <el-button type="primary" @click="ocistiSelekciju()" plain>Očisti selekciju</el-button>
    <el-button type="primary" @click="promjeniStanje()" plain>Promjeni stanje</el-button>
    <el-popconfirm
  title="Da li ste sigurni da želite izbrisati lijek?"
>
<template #reference>
    <el-button type="primary" @click="izbrisiLijek()" plain>Izbrisi lijek</el-button>
   </template>
  </el-popconfirm>
    
    <el-button @click="otvoriProzor" plain type="primary">
        Promjeni cijenu lijeka
    </el-button>
    
    <ModalniProzorCijena  ref="prozor"/>
  </div>
</template>  
 

<script>
import LijekoviTabela from  './LijekoviTabela'
import ModalniProzorCijena from './modal/ModalniProzorCijena'
export default {
  name: 'APMagacinLijekovi',
  data() {
      return {
        greska : false,
        poruka : "",
        prozor: false,
        modalOpen: false
      }
    },
    components:{
      LijekoviTabela,
      ModalniProzorCijena
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
      promjeniStanje(){
        this.$store.dispatch("APlijekovi/promjeniStanje",this.$refs.dijete.multipleSelection)
        if(this.$store.state.APlijekovi.zabranjeni.length!=0){
        this.greska=true;
        this.poruka = `Lijekovi sa identifikatorima: ${this.$store.state.APlijekovi.zabranjeni.join(',')} ne mogu biti prebaceni u magacin jer
         se nalaze u nekoj od rezervacija pacijenata`;
        }
        else{
          this.greaska = false;
          
        }
        this.$refs.dijete.$refs.multipleTable.clearSelection();
      },
      izbrisiLijek(){
        var greska = false;
        this.$refs.dijete.multipleSelection.forEach(element => {
          if(element.prodaja){
            greska = true;
          }
        });
        if(greska){
          this.greska=true;
          this.poruka = `Samo lijekovi koji se nalaze u prodaji mogu biti izbrisani`;
          return;
        }
        else{
          this.$store.dispatch("APlijekovi/izbrisiLijekove",this.$refs.dijete.multipleSelection)
        }
      },
      otvoriProzor(){

        if(this.$refs.dijete.multipleSelection.length!=1 ){
          this.greska=true;
          this.poruka = `Samo jedan lijek treba biti selektovan pri mijenjanju cijene`;
        }
        else{

        this.$refs.prozor.lijek.cijena = this.$refs.dijete.multipleSelection[0].cijena
        this.$refs.prozor.lijek.id = this.$refs.dijete.multipleSelection[0].id
        this.$refs.prozor.lijek.datumIstekaCijene = new Date(this.$refs.dijete.multipleSelection[0].datumIstekaCijene)
        this.$refs.prozor.modalOpen = true;

        }

      },
      selektujRedove(val) {
        this.$refs.dijete.multipleSelection = val;
      }
    }
  }

</script>