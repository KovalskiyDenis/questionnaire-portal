<template>
    <div class="container">
        <navbar full-navbar="true"></navbar>
        <div class="d-flex justify-content-center">
            <div class="card w-75">
                <div class="card-body">
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-auto mr-auto">
                                    <label class="align-bottom">Fields</label>
                                </div>
                                <div class="col-auto align-self-end">
                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal">ADD FIELD</button>
                                </div>


                                <div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">

                                            <div class="modal-header">
                                                <h5 class="modal-title" id="modalLabel">Add Field</h5>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>

                                            <div class="modal-body">

                                                <div class="form-group row">
                                                    <div class="col-3">
                                                        <label class="col-form-label d-flex justify-content-end" for="label">Label</label>
                                                    </div>
                                                    <div class="col-6">
                                                        <input class="d-flex flex-fill form-control" id="label" type="text" v-model="label">
                                                    </div>
                                                </div>

                                                <div class="form-group mt-3 row">
                                                    <div class="col-3">
                                                        <label class="col-form-label d-flex justify-content-end" for="typeSelector">Type</label>
                                                    </div>
                                                    <div class="col-6">
                                                        <select class="d-flex flex-fill custom-select" id="typeSelector" v-model="selected" v-on:change="showOptions">
                                                            <option v-for="option in optionsInput" v-bind:value="option.value">{{option.text}}</option>
                                                        </select>
                                                    </div>
                                                </div>

                                                <div v-bind:style="{display: displayOptions}">
                                                    <div class="form-group mt-3 row">
                                                        <div class="col-3">
                                                            <label class="col-form-label d-flex justify-content-end" for="optionsField">Options</label>
                                                        </div>
                                                        <div class="col-6">
                                                            <textarea class="d-flex flex-fill form-control" id="optionsField" name="options" rows="3" cols="5"></textarea>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col d-flex justify-content-end form-group form-check">
                                                        <div>
                                                            <input name="required" class="form-check-input " type="checkbox" id="required" v-model="required">
                                                        </div>
                                                        <div>
                                                            <label class="form-check-label" for="required">Required</label>
                                                        </div>
                                                    </div>
                                                    <div class="col form-group form-check">
                                                        <div>
                                                            <input name="active" class="form-check-input" type="checkbox" id="active" v-model="isActive">
                                                        </div>
                                                        <div>
                                                            <label class="form-check-label" for="active">Is Active</label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">CANCEL</button>
                                                <button type="submit" class="btn btn-primary" @click="saveField">SAVE</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </li>
                        <li class="list-group-item"></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import Navbar from 'components/navbar/Navbar.vue'

    export default {
        components: {
            Navbar
        },
        data() {
            return {
                label: null,
                selected: 'SINGLE_LINE_TEXT',
                options: null,
                required: false,
                isActive: false,
                optionsInput: [
                    {text: 'Single line text', value : 'SINGLE_LINE_TEXT'},
                    {text: 'Multiline text', value : 'MULTILINE_TEXT'},
                    {text: 'Radio button', value : 'RADIO_BUTTON'},
                    {text: 'Checkbox', value : 'CHECKBOX'},
                    {text: 'Combobox', value : 'COMBOBOX'},
                    {text: 'Date', value : 'DATE'}
                ],
                displayOptions: 'none',
            }
        },
        name: "FieldsPage",
        methods: {
            /*dosmth() {
                const accessToken = 'Bearer_' + localStorage.getItem("token")
                fetch('/fields', {method:'post', headers: {'Content-Type': 'application/json', 'Authorization': accessToken}, body: JSON.stringify({data: 'qwer'})}).then(console.log)
            },*/
            showOptions() {
                if(this.selected == 'RADIO_BUTTON' || this.selected == 'COMBOBOX') {
                    this.displayOptions = 'block'
                }  else {
                    this.displayOptions = 'none';
                }
            },
            saveField() {
                const accessToken = 'Bearer_' + localStorage.getItem("token")
                console.log(this.required)
                console.log(this.isActive)
                fetch('/fields', {method:'post', headers: {'Content-Type': 'application/json', 'Authorization': accessToken}, body: JSON.stringify({
                        label: this.label, type: this.selected, options: this.options, isRequired: this.required, isActive: this.isActive
                    })})
                    .then(console.log)
            }
        },
        mounted() {
        }
    }
</script>

<style scoped>

</style>