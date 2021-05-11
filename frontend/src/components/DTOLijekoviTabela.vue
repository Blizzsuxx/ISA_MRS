<template>
    <el-table
      :data="lijekovi.filter(data => !search || String(data.naziv).toLowerCase().includes(search.toLowerCase()) ||
        String(data.vrstaLijeka).toLowerCase().includes(search.toLowerCase())
        || String(data.ocijena).toLowerCase() === search.toLowerCase()
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
        property="poeni"
        label="Poeni"
        width="180">
      </el-table-column>
      <el-table-column
      align="right">
        <template #header>
          <el-input
            v-model="search"
            size="mini"
            placeholder="Pretraga"/>
        </template>
        <template #default="scope">
        <el-button
          size="mini"
          @click="handleInfo(scope.$index, scope.row)">Edit</el-button>
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)">Delete</el-button>
      </template>
      </el-table-column>
    </el-table>


  <el-dialog
    title="Informacije o Lijeku"
    v-model="centerDialogVisible"
    width="30%"
    destroy-on-close
    center>
    <div style="display: flex;
  justify-content: center;  padding-bottom: 20px;">
    <el-image  style="width: 100px; height: 100px; border: 1px solid;" :src="url" :fit="fit">
      <template #error>
        <div class="image-slot">
          <i class="el-icon-info"></i>
        </div>
      </template>
    </el-image>
    </div>
    <div>
      <p><strong>Naziv</strong></p>
      <el-input :model-value='lijek.naziv' v-model="lijek.naziv" readonly></el-input>
      <p><strong>Napomena</strong></p>
      <el-input  v-model="lijek.napomena"></el-input>
      <p><strong>Oblik Lijeka</strong></p>
      <el-input  v-model="lijek.oblikLijeka"></el-input>
      <p><strong>Ocijena</strong></p>
      <el-input v-model="lijek.ocijena"></el-input>
      <p><strong>Proizvodjac</strong></p>
      <el-input v-model="lijek.proizvodjac"></el-input>
      <p><strong>Vrsta Lijeka</strong></p>
      <el-input v-model="lijek.vrstaLijeka"></el-input>
      <p><strong>Sastav</strong></p>
      <el-input v-model="lijek.sastav"></el-input>
      <p><strong>Poeni</strong></p>
      <el-input v-model.number="lijek.poeni"></el-input>
    </div>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="centerDialogVisible = false">Izadji</el-button>
        <el-button type="primary" @click="handleEdit">Potvrdi</el-button>
      </span>
    </template>

  </el-dialog>


</template>

<script>
export default {
    name: 'DTOLijekoviTabela',
    data() { 
        return {
            name: 'DTOLijekoviTabela',
            search: '',
            centerDialogVisible: false,
            lijek: {
              napomena: "",
              naziv: "",
              oblikLijeka: "",
              ocijena: "",
              proizvodjac: "",
              sastav: "",
              vrstaLijeka: ""
            }
        }
    },
    methods:{
      handleInfo: function(index, row){
        this.$store.dispatch("APlijekovi/dobaviDTOLijek", row.naziv)
        .then(response => {
              this.lijek = response.data;
              this.centerDialogVisible = true;
        });
      },
      handleEdit: function(){
          this.centerDialogVisible = false;
          this.$store.dispatch("APlijekovi/azurirajDTOLijek", this.lijek)
          .then(response => {
            this.funkcija();
             return response;
          });
      },
      handleDelete: function(index, row){
    
        this.$store.dispatch("APlijekovi/obrisiDTOLijek", row.naziv)
          .then(response => {
            this.funkcija();
             return response;
          });
      }
    },
    mounted() {
   
    },
    props: ['lijekovi', 'funkcija']
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