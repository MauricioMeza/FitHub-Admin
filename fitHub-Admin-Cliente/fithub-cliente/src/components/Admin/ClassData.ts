class classData {
     getClassData = (clasData: [{id: string, sesion: string, instructor: string, fecha: Date, }]) => {
        var dataSource: {Id:string, Subject:string, EndTime:Date, StartTime:Date, IsAllDay:boolean, Instructor:string}[] =[];
        clasData.forEach(clas => {
            let fechaClase = new Date(clas.fecha)
            var present = false;
            if (fechaClase < new Date()){
                present = true;
            }
            var clasDataS = {
                Id: clas.id,
                Subject: clas.sesion,
                EndTime: new Date(fechaClase.getTime() + 40 * 60000),
                StartTime: fechaClase,
                IsAllDay: false,
                Instructor: clas.instructor,
                IsReadOnly: present
            } 
            dataSource.push(clasDataS);
        });
        return dataSource
    }
}

export default new classData();