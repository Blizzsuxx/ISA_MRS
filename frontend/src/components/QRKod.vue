<template>
    <NavMeniZaPacijenta/>
    <el-upload
  class="upload-demo"
  action="https://jsonplaceholder.typicode.com/posts/"
  :on-preview="handlePreview"
  :on-remove="handleRemove"
  :before-remove="beforeRemove"
  multiple
  :limit="1"
  :on-exceed="handleExceed"
  :file-list="fileList">
        <el-button size="small" type="primary">Ucitaj</el-button>
        <template #tip>
        <div class="el-upload__tip">jpg/png files with a size less than 500kb</div>
        </template>
    </el-upload>

    <el-table
    :data="apoteke.filter(data => !search)"
    style="width: 100%">
    <el-table-column
      sortable
      label="Ime"
      prop="ime">
    </el-table-column>
    <el-table-column
      sortable
      label="Mjesto"
      prop="mjesto">
    </el-table-column>
     <el-table-column
      sortable
      label="Ukupna Cijena"
      prop="ukupnaCijena">
    </el-table-column>
    <el-table-column
      sortable
      label="Prosjecna Ocijena"
      prop="prosecnaOcena">
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
          @click="rezervisi(scope.$index, scope.row)">Rezervisi</el-button>
      </template>
    </el-table-column>
  </el-table>

    <el-dialog
    title="Sadrzaj ponude u apoteci"
    v-model="prviProzor"
    width="40%"
    destroy-on-close
    center>
    <div style="display: flex;
  justify-content: center;  padding-bottom: 20px;">
    </div>
    <el-table
    :data="sadrzajApoteke.stanja"
    style="width: 100%">
    <el-table-column
      label="Lijek"
      prop="lijek.naziv">
    </el-table-column>
    <el-table-column
      label="Kolicina"
      prop="kolicina">
    </el-table-column>
    <el-table-column
      label="Apoteka"
      prop="imeApoteke">
    </el-table-column>
    <el-table-column
      align="right">
      <template #header>
        <el-input
          v-model="searchOdgovori"
          size="mini"
          placeholder="Type to search"/>
      </template>
    </el-table-column>
  </el-table>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="prviProzor = false">Izadji</el-button>
      </span>
    </template>

    </el-dialog>
</template>

<script>
import NavMeniZaPacijenta from "./NavMeniZaPacijenta.vue"
export default {
    name: 'QRKod',
    data()  {
        return {
            fileList: [],
            apoteke: [],
            search: '',
            prviProzor: false,
            searchOdgovori: '',
            sadrzajApoteke: {}
        }
        
    },
    mounted() {
        
    },
    methods: {
        open1() {
        this.$message({
          showClose: true,
          message: 'Uspjesno kreiran erecept.',
          type: 'success'
        })}
        ,
        open2() {
        this.$message({
          showClose: true,
          message: 'Nije validan qrkod.',
          type: 'error'
        })}
        ,
        handleRemove(file, fileList) {
            console.log(file, fileList)
        },
        handlePreview(file) {
           
            this.$store.dispatch('QRKod/posaljiKod', file.name)
            .then(response => {
                
                if (response.data === ''){
                    
                    this.open2();
                } else {
                  this.apoteke = response.data;
              
                }
              
            });
        },
        handleExceed(files) {
            this.$message.warning(
            `Limit je 1, a ne ${
                files.length + 1
            }, jer je 1 maksimalno.`,);
            console.log(files);
        },
        beforeRemove(file) {
            return this.$confirm(`Zaustavljen transfer ${file.name} ?`)
        },
        sadrzaj(index, row) {
            this.sadrzajApoteke = row;
            console.log(this.sadrzajApoteke);
            this.prviProzor = true;
        },
        rezervisi(index, row) {
            this.sadrzajApoteke = row;
            this.$store.dispatch('QRKod/rezervisiERecept', this.sadrzajApoteke)
            .then(response=>{
                if (response.data){
                    this.open1();
                } else {
                  this.open2();
                }
            });
        }
    },
    components : {
       NavMeniZaPacijenta
    },
    
  }
</script>