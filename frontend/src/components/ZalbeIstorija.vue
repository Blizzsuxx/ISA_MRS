<style scoped>
  body {
    margin: 10;
    padding: 10;
    background-color: #17a2b8;
    height: 100vh;
  }

  .grupa {
    width: 200px;
  }

  #unos {
    margin: auto;
    width: 50%;
    padding: 10px;
    
  }
  #unos .container #unos-row #unos-column #unos-box {
    margin: auto;
    width: 50%;
    margin-top: 10px;
    max-width: 750px;
    height: 650px;
    border: 1px solid #9C9C9C;
    background-color: #EAEAEA;
    font-weight: bold;
  }

  #unos .container #unos-row #unos-column #unos-box #unos-form {
    padding: 20px;
  }

  #unos-link {
    font-weight: normal;
    font-style: italic;
  }
</style>

<template>
<NavMeniZaPacijenta/>

    <el-table
    :data="zalbe.filter(data => !search || data.naslov.includes(search.toLowerCase()) || data.datumVrijeme.includes(search.toLowerCase())
                      || data.pacijent.includes(search.toLowerCase()))"
    style="width: 100%">
    <el-table-column
      label="Naslov"
      prop="naslov">
    </el-table-column>
    <el-table-column
      label="Pacijent"
      prop="pacijent">
    </el-table-column>
    <el-table-column
      label="Datum i Vrijeme"
      prop="datumVrijeme">
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
          @click="sadrzaj(scope.$index, scope.row)">Sadrzaj</el-button>
        <el-button
          size="mini"
          @click="odgovori(scope.$index, scope.row)">Odgovori</el-button>
      </template>
    </el-table-column>
  </el-table>

  <el-dialog
    title="Sadrzaj Zalbe"
    v-model="centerDialogVisible"
    width="30%"
    destroy-on-close
    center>
    <div style="display: flex;
  justify-content: center;  padding-bottom: 20px;">
    </div>
    <div>
      <p>{{zalba.text}}</p>
    </div>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="centerDialogVisible = false">Izadji</el-button>
      </span>
    </template>
    </el-dialog>
</template>

<script>
import NavMeniZaPacijenta from "./NavMeniZaPacijenta.vue"
  export default {
    name: 'ZalbeIstorija',
    data() {
      return {
        zalbe: [],
        search: '',
        centerDialogVisible: false,
        zalba: {}
      };
    },
    components : {
       NavMeniZaPacijenta
    },
    mounted(){
        this.$store.dispatch('APKorisnici/dobaviZalbe')
        .then(response => {
            this.zalbe = response.data;
        })
    },
    methods: {
        sadrzaj(index, row) {
            console.log(index);
            this.centerDialogVisible = true;
            this.zalba = row;
        },
        odgovori(index, row) {
            console.log(index);
            console.log(row);
        }
    }
  }
</script>