import django.contrib.admin

import vacancy.models

django.contrib.admin.site.register(vacancy.models.Vacancy)
django.contrib.admin.site.register(vacancy.models.EchoVacancy)
