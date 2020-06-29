<template xmlns:v-for="http://www.w3.org/1999/xhtml">
    <div class="container">
        <navbar full-navbar="true"></navbar>
        <div class="d-flex justify-content-center">
            <div class="card w-75">
                <div class="card-body">
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-auto mr-auto">
                                    <h3>Fields</h3>
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
                                                            <textarea class="d-flex flex-fill form-control" id="optionsField" rows="3" cols="5" v-model="options"></textarea>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col d-flex justify-content-end form-group form-check">
                                                        <div>
                                                            <input name="required" class="form-check-input " type="checkbox" id="required" v-model="isRequired">
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
                                                <button type="submit" class="btn btn-primary" data-dismiss="modal" @click="saveField">SAVE</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li v-if="showFields" class="list-group-item">
                            <table class="table table-striped table-sm table-borderless">
                                <thead>
                                <tr>
                                    <th scope="col">Label</th>
                                    <th scope="col">Type</th>
                                    <th scope="col">Required</th>
                                    <th scope="col">Is Active</th>
                                    <th scope="col"></th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr v-for="field in fields">
                                    <td>{{field.label}}</td>
                                    <td>{{field.type}}</td>
                                    <td>{{field.isRequired}}</td>
                                    <td>{{field.isActive}}</td>
                                    <td>
                                        <button class="btn" @click="showEditFieldForm(field.id)" data-toggle="modal" data-target="#editModal">
                                            <svg class="bi bi-pencil-square" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456l-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                                                <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                                            </svg>
                                        </button>
                                        <button class="btn" @click="removeField(field.id)">
                                            <svg class="bi bi-trash-fill" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                <path fill-rule="evenodd" d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5a.5.5 0 0 0-1 0v7a.5.5 0 0 0 1 0v-7z"/>
                                            </svg>
                                        </button>

                                    </td>
                                </tr>


                                <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">

                                            <div class="modal-header">
                                                <h5 class="modal-title" id="editModalLabel">Add Field</h5>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>

                                            <div class="modal-body">

                                                <div class="form-group row">
                                                    <div class="col-3">
                                                        <label class="col-form-label d-flex justify-content-end" for="editLabel">Label</label>
                                                    </div>
                                                    <div class="col-6">
                                                        <input class="d-flex flex-fill form-control" id="editLabel" type="text" v-model="editFieldData.editLabel">
                                                    </div>
                                                </div>

                                                <div class="form-group mt-3 row">
                                                    <div class="col-3">
                                                        <label class="col-form-label d-flex justify-content-end" for="editTypeSelector">Type</label>
                                                    </div>
                                                    <div class="col-6">
                                                        <select class="d-flex flex-fill custom-select" id="editTypeSelector" v-model="editFieldData.editType" v-on:change="showOptionsForEditForm">
                                                            <option v-for="optionEdit in optionsInput" v-bind:value="optionEdit.value">{{optionEdit.text}}</option>
                                                        </select>
                                                    </div>
                                                </div>

                                                <div v-bind:style="{display: displayOptionsForEditForm}">
                                                    <div class="form-group mt-3 row">
                                                        <div class="col-3">
                                                            <label class="col-form-label d-flex justify-content-end" for="editOptionsField">Options</label>
                                                        </div>
                                                        <div class="col-6">
                                                            <textarea class="d-flex flex-fill form-control" id="editOptionsField" rows="3" cols="5" v-model="editFieldData.editOptions">
                                                            </textarea>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col d-flex justify-content-end form-group form-check">
                                                        <div>
                                                            <input name="required" class="form-check-input " type="checkbox" id="editIsRequired" v-model="editFieldData.editIsRequire">
                                                        </div>
                                                        <div>
                                                            <label class="form-check-label" for="editIsRequired">Required</label>
                                                        </div>
                                                    </div>
                                                    <div class="col form-group form-check">
                                                        <div>
                                                            <input name="active" class="form-check-input" type="checkbox" id="editIsActive" v-model="editFieldData.editIsActive">
                                                        </div>
                                                        <div>
                                                            <label class="form-check-label" for="editIsActive">Is Active</label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">CANCEL</button>
                                                <button type="submit" class="btn btn-primary" data-dismiss="modal" @click="saveEditedField(editFieldData.editId)">SAVE</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                                </tbody>
                            </table>
                        </li>
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
                showFields: false,

                fields: [],

                label: null,
                selected: 'SINGLE_LINE_TEXT',
                options: '',
                isRequired: false,
                isActive: false,
                optionsInput: [
                    {text: 'Single line text', value: 'SINGLE_LINE_TEXT'},
                    {text: 'Multiline text', value: 'MULTILINE_TEXT'},
                    {text: 'Radio button', value: 'RADIO_BUTTON'},
                    {text: 'Checkbox', value: 'CHECKBOX'},
                    {text: 'Combobox', value: 'COMBOBOX'},
                    {text: 'Date', value: 'DATE'}
                ],
                displayOptions: 'none',
                displayOptionsForEditForm: 'none',

                editFieldData: {
                    editId: null,
                    editLabel: null,
                    editType: null,
                    editOptionsArray: [],
                    editOptions: null,
                    editIsRequire: null,
                    editIsActive: null
                },

                valid: true,

                alerts: {
                    labelAlerts: []
                }
            }
        },
        name: "FieldsPage",
        methods: {
            showOptions() {
                if (this.selected === 'RADIO_BUTTON' || this.selected === 'COMBOBOX' || this.selected === 'CHECKBOX') {
                    this.displayOptions = 'block'
                } else {
                    this.displayOptions = 'none';
                }
            },
            showOptionsForEditForm() {
                if (this.editFieldData.editType === 'RADIO_BUTTON' || this.editFieldData.editType === 'COMBOBOX' || this.editFieldData.editType === 'CHECKBOX') {
                    this.displayOptionsForEditForm = 'block'
                } else {
                    this.displayOptionsForEditForm = 'none';
                }
            },
            saveField() {
                //this.validateForm()
                if(this.valid) {
                    this.$resource('/fieldsPage').save({
                        label: this.label,
                        type: this.selected,
                        options: this.options.split('\n'),
                        isRequired: this.isRequired ? 'True' : 'False',
                        isActive: this.isActive ? 'True' : 'False',
                    }).then()
                    this.showFields = true

                    this.label = null
                    this.options = ''
                    this.selected = 'SINGLE_LINE_TEXT'
                    this.isRequired = false
                    this.isActive = false
                }

            },
            saveEditedField(id) {
                //this.validateForm()
                if(this.valid) {
                    this.$resource('/fieldsPage').update({
                        id: id,
                        label: this.editFieldData.editLabel,
                        type: this.editFieldData.editType,
                        options: this.editFieldData.editOptions.split('\n'),
                        isRequired: this.editFieldData.editIsRequire ? 'True' : 'False',
                        isActive: this.editFieldData.editIsActive ? 'True' : 'False',
                    }).then(result=> {
                        if(result.ok) {
                            let field = result.data
                            const index = this.findById(field.id)
                            this.fields[index] = field
                        }
                    })
                }
            },

            showEditFieldForm(fieldId) {
                this.alerts.labelAlerts = []

                const i = this.findById(fieldId)
                if(i > -1) {
                    this.editFieldData.editId = fieldId
                    this.editFieldData.editLabel = this.fields[i].label
                    this.editFieldData.editType = this.fields[i].type
                    this.editFieldData.editOptions = this.fields[i].options

                    this.editFieldData.editOptionsArray = this.fields[i].options
                    this.editFieldData.editOptions = ''

                    const length =  this.editFieldData.editOptionsArray.length
                    let j = 0
                    for(j; j < length - 1; j++) {
                        this.editFieldData.editOptions += this.editFieldData.editOptionsArray[j]
                        this.editFieldData.editOptions += '\n'
                    }
                    this.editFieldData.editOptions += this.editFieldData.editOptionsArray[j]

                    this.editFieldData.editIsRequire = this.fields[i].isRequired === 'True'
                    this.editFieldData.editIsActive = this.fields[i].isActive === 'True'
                    this.displayOptionsForEditForm = (this.editFieldData.editType === 'RADIO_BUTTON' || this.editFieldData.editType === 'COMBOBOX' || this.editFieldData.editType === 'CHECKBOX') ? 'block' : 'none'
                }
            },
            findById(id) {
                for(let i = 0; i < this.fields.length; i++) {
                    if(this.fields[i].id === id) {
                        return i
                    }
                }
                return -1;
            },
            removeField(fieldId) {
                let url = '/fieldsPage/' + fieldId
                this.$resource(url).delete().then(result => {
                    if(result.ok) {
                        this.fields.splice(this.findById(fieldId), 1)
                    }
                })
            },

            validateForm() {
                this.valid = true

                //label
                this.alerts.labelAlerts = []
                if ((this.label === '' || this.email == null) || (this.editFieldData.editLabel === '' || this.editFieldData.editLabel)) {
                    this.alerts.labelAlerts.push('Label is required')
                    this.valid = false
                }
            }
        },
        created() {
            this.$resource('/fieldsPage').get().then(result => {
                if (result.ok) {
                    this.fields = result.data.fields

                    if(this.fields.length != 0){
                        this.showFields = true
                    }
                }
            })
        }
    }
</script>

<style scoped>

</style>