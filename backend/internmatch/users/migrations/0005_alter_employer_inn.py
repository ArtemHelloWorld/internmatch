# Generated by Django 3.2.16 on 2024-10-29 09:06

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('users', '0004_auto_20241029_0855'),
    ]

    operations = [
        migrations.AlterField(
            model_name='employer',
            name='inn',
            field=models.CharField(blank=True, max_length=12, null=True, verbose_name='ИНН'),
        ),
    ]
