import {EventSettingsModel} from "@syncfusion/ej2-react-schedule";
import {DataManager, WebApiAdaptor} from "@syncfusion/ej2-data";

class classData {
     getClassData = (clasData: [{id: string, sesion: string, instructor: string, fecha: Date, }]) => {
        var dataSource: {Id:string, Subject:string, EndTime:Date, StartTime:Date, IsAllDay:boolean, IsReadonly:boolean}[] =[];
        clasData.forEach(clas => {
            let fechaClase = new Date(clas.fecha) 
            var clasDataS = {
                Id: clas.id,
                Subject: clas.sesion,
                EndTime: new Date(fechaClase.getTime() + 40 * 60000),
                StartTime: fechaClase,
                IsAllDay: false,
                IsReadonly: true
            }
            dataSource.push(clasDataS);
        });

        console.log(dataSource)
        return dataSource
    }
}

export default new classData();