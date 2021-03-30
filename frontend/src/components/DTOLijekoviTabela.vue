<template>
    <el-table
      :data="lijekovi.filter(data => !pretraga || String(data.naziv).toLowerCase().includes(pretraga.toLowerCase()) ||
        String(data.vrstaLijeka).toLowerCase().includes(pretraga.toLowerCase())
        || String(data.ocijena).toLowerCase() === pretraga.toLowerCase()
      )"
      style="width: 100%">
      <el-table-column
        property="naziv"
        label="Naziv"
        width="180">
        <template #default="scope">
          <el-popover effect="light" trigger="hover" placement="top">
            <template #default>
              <p>Naziv: {{ scope.row.naziv }}</p>
              <p>Vrsta: {{ scope.row.vrstaLijeka }}</p>
            </template>
            <template #reference>
              <div class="name-wrapper">
                <el-tag size="medium">{{ scope.row.naziv }}</el-tag>
              </div>
            </template>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
        property="vrstaLijeka"
        label="Vrsta Lijeka"
        width="180">
      </el-table-column>
      <el-table-column
        property="ocijena"
        label="Ocijena"
        width="180">
      </el-table-column>
      <el-table-column
      align="right">
        <template #header>
          <el-input
            v-model="pretraga"
            size="mini"
            placeholder="Pretraga"/>
        </template>
        <template #default="scope">
          <el-button
            size="mini"
            @click="prikaz(scope.$index, scope.row)">Informacija</el-button>
        </template>
      </el-table-column>
    </el-table>

    <teleport to="body">
    <div v-if="modalniProzor" id="myModal" class="modal">
      <div class="modal-content">
        <div class="modal-header">
          <span @click="modalniProzor=false" class="close">&times;</span>
          <h2>{{modalniInfo.naziv}}</h2>
        </div>
        <div class="modal-body">
          <p>{{modalniInfo.vrstaLijeka}}</p>
          <p>{{modalniInfo.ocijena}}</p>
        </div>
      </div>
    </div>
    </teleport>
</template>

<script>
export default {
    name: 'DTOLijekoviTabela',
    data() { 
        return {
            pretraga: '',
            modalniProzor: false,
            modalniInfo: {
              naziv: "",
              vrstaLijeka: "",
              ocijena: ""
            }
        }
    },
    methods:{
      prikaz: function(index, row){
        console.log(index + " " + row);
        this.modalniInfo.naziv = row.naziv;
        this.modalniInfo.vrstaLijeka = row.vrstaLijeka;
        this.modalniInfo.ocijena = row.ocijena;
        this.modalniProzor = true;
      }
    },
    mounted() {
   
    },
    props: ['lijekovi']
}
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