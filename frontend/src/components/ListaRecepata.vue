<template>
<el-main>
  <NavMeniZaPacijenta/>
<h2>Svi recepti pacijenta</h2>
<div>
  <el-table
    ref="podaci"
    :data="podaci"
    style="width: 100%"
    @selection-change="handleSelectionChange">
    <el-table-column
    sortable
      type="selection"
      width="55">
    </el-table-column>
    <el-table-column
    sortable
      property="sifra"
      label="Sifra"
      width="120">
    </el-table-column>
    <el-table-column
    sortable
      property="prepisaniLijekovi[0].apoteka.ime"
      label="Naziv aopteke"
      show-overflow-tooltip>
    </el-table-column>
     <el-table-column
     sortable
      property="datumIzdavanja"
      label="datum"
      show-overflow-tooltip>
    </el-table-column>
    <el-table-column
    sortable
      label="Prikazi lekove">
      <template #default="scope">
        <el-button
          size="mini"
          @click="handleEdit(scope.$index, scope.row)">Prikazi lekove</el-button>
        
      </template>
    </el-table-column>
   
  </el-table> 
  </div>
  <br>
   <div>
    <el-table
    ref="modalniInfo.lekovi"
    :data="modalniInfo.lekovi"
    style="width: 100%"
    @selection-change="handleSelectionChange">
    <el-table-column
      type="selection"
      width="55">
    </el-table-column>
    <el-table-column
    sortable
      property="lijek.naziv"
      label="Naziv"
      width="120">
    </el-table-column>
    <el-table-column
    sortable
      property="lijek.vrsta"
      label="Vrsta"
      show-overflow-tooltip>
    </el-table-column>
    <el-table-column
    sortable
      property="cijena"
      label="Cena"
      show-overflow-tooltip>
    </el-table-column>
    <el-table-column
    sortable
      property="kolicina"
      label="Kolicina"
      show-overflow-tooltip>
    </el-table-column>
  </el-table>
</div>
</el-main>
</template>

const { defineComponent, ref } = Vue;
<script>
import NavMeniZaPacijenta from "./NavMeniZaPacijenta.vue"
   import { defineComponent, ref } from 'vue'
export default defineComponent ({
 components :{NavMeniZaPacijenta},
  setup() {
    return {
      input: ref('')
    }
  },
   data() { 
        return {
          podaci: [],
          modalniInfo: {
              naziv: "",
              lekovi: []
        },
        }
    },

    name: 'ListaRecepata',
    async mounted(){
     await this.$store.dispatch("ERecepti/dobaviRecepte")
     this.podaci=this.$store.state.ERecepti.sviRecepti;
    
    },
    methods: {
       handleEdit: function(index, row){
        console.log(row)
        this.modalniInfo.lekovi = this.podaci[index].prepisaniLijekovi
        //TODO pazi na sortirtanje, proveri
      },
     
    }
  })

</script>
<style scoped>
  .modal {
  
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    padding-top: 100px; /* Location of the box */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
  }
  
  /* Modal Content */
  .modal-content {
    position: relative;
    background-color: #fefefe;
    margin: auto;
    padding: 0;
    border: 1px solid #888;
    width: 80%;
    box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2),0 6px 20px 0 rgba(0,0,0,0.19);
    -webkit-animation-name: animatetop;
    -webkit-animation-duration: 0.4s;
    animation-name: animatetop;
    animation-duration: 0.4s
  }
  
  /* Add Animation */
  @-webkit-keyframes animatetop {
    from {top:-300px; opacity:0} 
    to {top:0; opacity:1}
  }
  
  @keyframes animatetop {
    from {top:-300px; opacity:0}
    to {top:0; opacity:1}
  }
  
  /* The Close Button */
  .close {
    color: white;
    float: right;
    font-size: 28px;
    font-weight: bold;
  }
  
  .close:hover,
  .close:focus {
    color: #000;
    text-decoration: none;
    cursor: pointer;
  }
  
  .modal-header {
    padding: 2px 16px;
    background-color: #5cb85c;
    color: white;
  }
  
  .modal-body {padding: 2px 16px;}
  
  .modal-footer {
    padding: 2px 16px;
    background-color: #5cb85c;
    color: white;
  }
</style>
