package be.ferrara.project2022.mpm.db;

public abstract class MPMDbSchema {
    public static final class StudentTable {
        public static final String NAME = "student";
        public static final class cols{
            public static final String NAME = "studentName";
            public static final String UUID = "studentUUID";
        }
    }
    public static final class ProjectTable{
        public static final String NAME = "project";
        public static final class cols{
            public static final String NAME = "projectName";
            public static final String DESCRIPTION = "description";
            public static final String UUID = "projectUUID";
            public static final String STUDENTUUID = "studentUUID";
        }
    }
    public static final class StepTable{
        public static final String NAME = "step";
        public static final class cols{
            public static final String PROJECTUUID ="projectUUID";
            public static final String NAME = "name";
            public static final String POINTS = "points";
        }
    }
}
