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
      label="Id"
      prop="id">
    </el-table-column>
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
    v-model="prviProzor"
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
        <el-button @click="prviProzor = false">Izadji</el-button>
      </span>
    </template>
    </el-dialog>

     <el-dialog
    title="Odgovori na Zalbu"
    v-model="drugiProzor"
    width="40%"
    destroy-on-close
    center>
    <div style="display: flex;
  justify-content: center;  padding-bottom: 20px;">
    </div>
    <el-table
    :data="odgovoriZalbe.filter(data => !searchOdgovori || data.naslov.includes(searchOdgovori.toLowerCase()) || data.datumVrijeme.includes(searchOdgovori.toLowerCase())
                      || data.pacijent.includes(searchOdgovori.toLowerCase()))"
    style="width: 100%">
    <el-table-column
      label="Naslov"
      prop="naslov">
    </el-table-column>
    <el-table-column
      label="Administrator"
      prop="administrator">
    </el-table-column>
    <el-table-column
      label="Datum i Vrijeme"
      prop="datumVrijeme">
    </el-table-column>
    <el-table-column
      align="right">
      <template #header>
        <el-input
          v-model="searchOdgovori"
          size="mini"
          placeholder="Type to search"/>
      </template>
      <template #default="scope">
        <el-button
          size="mini"
          @click="sadrzajOdgovora(scope.$index, scope.row)">Sadrzaj</el-button>
      </template>
    </el-table-column>
  </el-table>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="drugiProzor = false">Izadji</el-button>
      </span>
    </template>

    <el-dialog
    title="Sadrzaj Odgovora"
    v-model="treciProzor"
    width="30%"
    destroy-on-close
    center>
    <div style="display: flex;
  justify-content: center;  padding-bottom: 20px;">
    </div>
    <div>
      <p>{{odgovor.text}}</p>
    </div>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="treciProzor = false">Izadji</el-button>
      </span>
    </template>
    </el-dialog>

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
        prviProzor: false,
        drugiProzor: false,
        treciProzor: false,
        zalba: {},
        odgovoriZalbe: [],
        searchOdgovori: '',
        odgovor: {},
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
            this.prviProzor = true;
            this.zalba = row;
        },
        odgovori(index, row) {
            this.zalba = row;
            this.$store.dispatch('APKorisnici/dobaviOdgovore', row.id)
            .then(response => {
                this.odgovoriZalbe = response.data;
                this.drugiProzor = true;
            })


        },
        sadrzajOdgovora(index, row){
            console.log(index);
            console.log(row);
            this.treciProzor = true;
            this.odgovor = row;
        }
    }
  }
</script>