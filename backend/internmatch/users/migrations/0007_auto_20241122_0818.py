# Generated by Django 3.2.16 on 2024-11-22 08:18

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('users', '0006_skill_resume_link'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='skill',
            name='resume_link',
        ),
        migrations.AddField(
            model_name='intern',
            name='resume_link',
            field=models.TextField(blank=True, null=True, verbose_name='ссылка на резюме'),
        ),
    ]
