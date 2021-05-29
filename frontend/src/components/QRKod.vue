<template>
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
</template>

<script>

export default {
    name: 'QRKod',
    data()  {
        return {
          fileList: [
          
            ],
        }
        
    },
    mounted() {
        
    },
    methods: {
        handleRemove(file, fileList) {
            console.log(file, fileList)
        },
        handlePreview(file) {
            console.log(file.name);
            this.$store.dispatch('QRKod/posaljiKod', file.name);
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
    },
    components:{
       
    },
    
  }
</script>