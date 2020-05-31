class classData {
    getClassData = (clasData: [{id: string, sesion: string, instructor: string, fecha: Date, }], 
                    clasRes: [{id: string, sesion: string, instructor: string, fecha: Date, }]) => {
       var dataSource: {Id:string, Subject:string, EndTime:Date, StartTime:Date, IsAllDay:boolean, Instructor:string, Reserved:boolean}[] =[];
       console.log(clasRes);  
       clasData.forEach(clas => {
            var reserved = false;
            for (const clasR of clasRes) {
                if(clasR.id == clas.id){
                    reserved = true;
                }
            } 
            if(clasRes.includes(clas)){
                console.log(clas)
                reserved = true;
            }
            let fechaClase = new Date(clas.fecha) 
            var clasDataS = {
                Id: clas.id,
                Subject: clas.sesion,
                EndTime: new Date(fechaClase.getTime() + 40 * 60000),
                StartTime: fechaClase,
                IsAllDay: false,
                Instructor: clas.instructor,
                Reserved: reserved
            } 
            dataSource.push(clasDataS);
        });
        return dataSource
    }
}

export default new classData();