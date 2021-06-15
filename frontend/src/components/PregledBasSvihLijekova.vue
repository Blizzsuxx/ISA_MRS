<template>
  <el-table
    :data="tableData.filter(data => !search || data.sifra.toLowerCase().includes(search.toLowerCase())
    || data.naziv.toLowerCase().includes(search.toLowerCase())
    || data.vrstaLijeka.toLowerCase().includes(search.toLowerCase()))"
    style="width: 100%">
    <el-table-column
      label="Šifra"
      prop="sifra"
      sortable>
    </el-table-column>
    <el-table-column
      label="Naziv"
      prop="naziv"
      sortable>
    </el-table-column>
     <el-table-column
      label="Vrsta"
      prop="vrstaLijeka"
      sortable>
    </el-table-column>
     <el-table-column
      label="Ocijena"
      prop="ocijena"
      sortable>
    </el-table-column>
    <el-table-column
      align="right">
      <template #header>
        <el-input
          v-model="search"
          size="mini"
          placeholder="Type to search"/>
      </template>
      <template #default="scope">
        <el-button
          size="mini"
          @click="apoteke(scope.$index, scope.row)">Apoteke</el-button>
        <el-button
          size="mini"
          @click="informacije(scope.$index, scope.row)">Informacije</el-button>
      </template>
    </el-table-column>
  </el-table>

  <el-dialog
    title="Informacije o Lijeku"
    v-model="prviProzor"
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
      <el-input  v-model="lijek.napomena" readonly></el-input>
      <p><strong>Oblik Lijeka</strong></p>
      <el-input  v-model="lijek.oblikLijeka" readonly></el-input>
      <p><strong>Ocijena</strong></p>
      <el-input v-model="lijek.ocijena" readonly></el-input>
      <p><strong>Proizvodjac</strong></p>
      <el-input v-model="lijek.proizvodjac" readonly></el-input>
      <p><strong>Vrsta Lijeka</strong></p>
      <el-input v-model="lijek.vrstaLijeka" readonly></el-input>
      <p><strong>Sastav</strong></p>
      <el-input v-model="lijek.sastav" readonly></el-input>
      <p><strong>Poeni</strong></p>
      <el-input v-model.number="lijek.poeni" readonly></el-input>
      <p><strong>Preporuceni unos</strong></p>
      <el-input v-model="lijek.preporuceniUnos" readonly></el-input>
    </div>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="prviProzor = false">Izadji</el-button>
      </span>
    </template>
  </el-dialog>

  <el-dialog
    title="Apoteke i cijene"
    v-model="drugiProzor"
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
    
  <el-table
    :data="apotekeLijekovi"
    style="width: 100%">
    <el-table-column
      label="Naziv Apoteke"
      prop="nazivApoteke"
      sortable>
    </el-table-column>
    <el-table-column
      label="Cijena"
      prop="cijena"
      sortable>
    </el-table-column>
  </el-table>
  
    </div>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="drugiProzor = false">Izadji</el-button>
      </span>
    </template>
  </el-dialog>
    
</template>

<script>
  export default {
    data() {
      return {
        tableData: [],
        search: '',
        prviProzor: false,
        drugiProzor: false,
        lijek: {},
        apotekeLijekovi: []
      }
    },
    mounted() {
        this.$store.dispatch("APlijekovi/dobaviBasSveLijekove")
        .then(response => {
            this.tableData = response.data;
        })
    },
    methods: {
      apoteke(index, row) {
        console.log(index, row);
        this.lijek = row;
        this.$store.dispatch("APlijekovi/dobaviSveLijekoveApotekeDTO", this.lijek.id)
        .then(response => {
            this.apotekeLijekovi = response.data;
            this.drugiProzor = true;
        })
      },
      informacije(index, row) {
        console.log(index, row);
        this.lijek = row;
        this.prviProzor = true;
      }
    },
  }
</script>