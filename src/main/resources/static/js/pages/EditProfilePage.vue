<template>
    <div>
        <navbar :full-navbar="true"></navbar>
    </div>
</template>

<script>
    import Navbar from 'components/navbar/Navbar.vue'

    export default {
        components: {
            Navbar
        },
        name: "EditProfilePage"
    }
</script>

<style scoped>

</style>